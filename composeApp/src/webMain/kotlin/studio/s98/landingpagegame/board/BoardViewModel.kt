package studio.s98.landingpagegame.board

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import studio.s98.landingpagegame.data.MainRepositoryImp
import studio.s98.landingpagegame.util.Timer
import studio.s98.landingpagegame.util.formatMillisecondsToMinutes
import studio.s98.landingpagegame.util.generateRandomUUIDString
import studio.s98.landingpagegame.viewmodel.Word
import studio.s98.landingpagegame.models.Letter

class BoardViewModel() {

    private val viewModelScope = CoroutineScope(Dispatchers.Main)
    private var localCoroutineScope: CoroutineScope? = null

    private val _state = MutableStateFlow(
        BoardState()
    )
    val state = _state
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Companion.WhileSubscribed(5000),
            initialValue = BoardState()
        )


    private val _soundState = MutableStateFlow(
        SoundState()
    )

    val soundState = _soundState
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Companion.WhileSubscribed(5000),
            initialValue = SoundState()
        )
//        .toCommonStateFlow()

    private var timer: Timer? = null
    private var currentTime = 0L
    private val repository = MainRepositoryImp()
    private var selectedPath = ArrayList<Pair<Int, Int>>()
    private var totalTime: Long = 120000
    private var bB: BoardBuilder = BoardBuilder(rows = 5, columns = 4)

    fun onEvent(event: BoardEvents) {
        when (event) {
            is BoardEvents.GameStarted -> {
                initGame()
            }

            is BoardEvents.UserSwiped -> {
                checkSwiped()
            }

            is BoardEvents.LetterSwiped -> {
                createPath(event.positions)
            }

            is BoardEvents.OnPause -> {
                onPause()
            }

            is BoardEvents.OnResume -> {
                onResume()
            }

            is BoardEvents.OnCancel -> {
                onCleared()
            }

            is BoardEvents.LetterSwipedWithLetterInformation -> {
                checkSwiped(event.letter)
            }
        }
    }


    private fun initGame() {
        setDifficulty()
    }

    private fun startGame(wordsList: List<Word> = repository.generateNewWords(3)) {
        launchTimer(isResume = false)
        bB.build(ArrayList<Word>(wordsList))
        val list = ArrayList<String>()
        for (letter in wordsList) {
            list.add(letter.word)
        }
        updateState(
            grid = bB.getBoard(),
            points = wordsList.size * 100,
            isNavigate = false,
            stars = null,
            remainingAnswers = list,
        )
        newSound(type = SoundType.STARTED)
        localCoroutineScope?.cancel()
    }

    private fun setDifficulty() {
        val level = repository.getUserLevel()
        val wordsList: List<Word>
        if (level.level <= 10) {
            wordsList = repository.generateNewWords(3)
        } else if (level.level <= 20) {
            wordsList = repository.generateNewWords(2)
        } else {
            wordsList = repository.generateNewWords(2)
            totalTime = 45000
        }
        startGame(wordsList)
    }

    private fun checkSwiped() {
        if (isOnlyHorizontalAndVertical()) return

        var selectedWord = ""
        var userFoundTheWord = false
        var wordTolerance = 0
        for (pos in selectedPath) {
            val letter = bB.getBoard()[pos.first][pos.second]
            if (letter.isSelected) {
                if (wordTolerance == 0)
                    wordTolerance++
                else {
                    userFoundTheWord = true
                    break
                }
            }
            selectedWord += letter.letter
        }
        if (!userFoundTheWord && repository.isContained(selectedWord.trim())) {
            countPoints()
            disableTile()
            removeWord(selectedWord)
            checkIsWin()
            resetSwiped()
        } else {
            clearSwiped()
        }
    }

    private fun checkSwiped(letters: Set<Int>) {
        val x = ArrayList<Letter>()
        for (index in letters.toList()) {
            x.add(bB.getBoard().flatten()[index])
        }

        var selectedWord = ""
        var userFoundTheWord = false
        var wordTolerance = 0
        for (letter in x) {
            if (letter.isSelected) {
                if (wordTolerance == 0)
                    wordTolerance++
                else {
                    userFoundTheWord = true
                    break
                }
            }
            selectedWord += letter.letter
        }
        if (!userFoundTheWord && repository.isContained(selectedWord.trim())) {
            countPoints()
            disableTile(x)
            removeWord(selectedWord)
            checkIsWin()
            resetSwiped()
        } else {
            clearSwiped(x)
        }
    }

    private fun isOnlyHorizontalAndVertical(): Boolean {
        if (selectedPath.isNotEmpty()) {
            val isHorizontal = selectedPath.all { it.first == selectedPath.first().first }
            val isVertical = selectedPath.all { it.second == selectedPath.first().second }
            // Check for non-sequential swipe in either direction (optional, based on game rules)
            val isSequentialHorizontal =
                isHorizontal && selectedPath.map { it.second }.zipWithNext { a, b -> b - a }
                    .all { it == 1 || it == -1 }
            val isSequentialVertical =
                isVertical && selectedPath.map { it.first }.zipWithNext { a, b -> b - a }
                    .all { it == 1 || it == -1 }

            if (!(isHorizontal || isVertical) || !(isSequentialHorizontal || isSequentialVertical)) {
                clearSwiped()
                return true
            }
        }
        return false
    }

    private fun clearSwiped(list: List<Letter>) {
        wrongSwiped(list)
        MainScope().launch {
            delay(500)
            resetSwiped(list)
        }
    }

    private fun clearSwiped() {
        wrongSwiped()
        MainScope().launch {
            delay(500)
            resetSwiped()
        }
    }

    private fun wrongSwiped(list: List<Letter>) {
        val grid = bB.getBoard().flatten()
        for (letter in list) {
            grid.find { it.id == letter.id }?.isWrong = true
        }
        updateState(grid = grid.chunked(4))
        newSound(type = SoundType.WRONG_SWIPE)
    }

    private fun wrongSwiped() {
        val grid = bB.getBoard()
        for (pos in selectedPath) {
            val letter = grid[pos.first][pos.second]
            grid[pos.first][pos.second] =
                createLetter(
                    letter = letter,
                    isSelected = letter.isSelected,
                    isSwiped = false,
                    isWrong = true
                )
        }
        updateState(grid = grid)
        newSound(type = SoundType.WRONG_SWIPE)

    }

    private fun removeWord(selectedWord: String) {
        val temp = ArrayList(_state.value.remainingAnswers)
        temp.remove(selectedWord.reversed())
        temp.remove(selectedWord)
        _state.value.remainingAnswers = temp
    }

    private fun resetSwiped() {
        val grid = bB.getBoard()
        for (pos in selectedPath) {
            val letter = grid[pos.first][pos.second]
            grid[pos.first][pos.second] =
                createLetter(
                    letter = letter,
                    isSelected = letter.isSelected,
                    isSwiped = false,
                    isWrong = false
                )
        }
        updateState(grid = grid)
        selectedPath = ArrayList()
    }

    private fun resetSwiped(list: List<Letter>) {
        val grid = bB.getBoard().flatten()
        for (letter in list) {
            grid.find { it.id == letter.id }?.isWrong = false

        }
        updateState(grid = grid.chunked(4))
    }

    private fun createPath(positions: List<Int>) {
        val pos = Pair(positions[0], positions[1])
        if (!selectedPath.contains(pos)) {
            val grid = bB.getBoard()
            val letter = grid[positions[0]][positions[1]]
            grid[positions[0]][positions[1]] =
                createLetter(letter = letter, isSelected = letter.isSelected, isSwiped = true)
            updateState(
                grid = grid
            )
            selectedPath.add(pos)
        }
    }

    private fun launchTimer(isResume: Boolean) {
        currentTime = if (isResume) currentTime else totalTime
        var isHalfTime = true
        timer = Timer(totalTime) {
            if (currentTime < 0) {
                onTimeFinished()
                return@Timer
            }

            updateState(
                percent = currentTime / totalTime.toFloat() * 100,
                time = formatMillisecondsToMinutes(currentTime),
            )
            currentTime -= 1000
            if ((currentTime / totalTime.toFloat() * 100) < 50 && isHalfTime) {
                isHalfTime = false
                updateState(falehFeel = FalehFeel.SLEEP)
                newSound(type = SoundType.HALF_TIME)
            }
        }
        timer?.startTimer()
    }

    private fun onPause() {
        timer?.cancelTimer()
    }

    private fun onResume() {
        launchTimer(true)
    }

    private fun onCleared() {
        timer?.cancelTimer()
        resetGrid()
    }

    private fun onTimeFinished() {
        updateState(isNavigate = true, stars = 0, percent = 100f)
        newSound(type = SoundType.IDLE)
        onCleared()
    }

    private fun onWin() {
        val stars = calculateStars()
        addXP(stars)
        updateState(stars = stars)
        onCleared()
    }

    private fun calculateStars(): Int {
        var stars = 0
        val timeLeftPercentage = ((currentTime).toDouble() / totalTime) * 100
        when {
            timeLeftPercentage > 75 -> stars = 3
            timeLeftPercentage > 50 -> stars = 2
            timeLeftPercentage < 50 -> stars = 1
            timeLeftPercentage <= 0 -> stars = 0
        }
        return stars
    }

    private fun addXP(stars: Int) {

        repository.addXP(stars)

    }

    private fun countPoints() {
        val newPoints = _state.value.points - 100
        updateState(points = newPoints, falehFeel = FalehFeel.CORRECT)
    }


    private fun disableTile() {
        val grid = bB.getBoard()
        for (pos in selectedPath) {
            val letter = grid[pos.first][pos.second]
            grid[pos.first][pos.second] =
                createLetter(letter = letter, isSelected = true, isSwiped = false)
        }
        updateState(grid = grid)
    }

    private fun disableTile(list: List<Letter>) {
        val grid = bB.getBoard().flatten()
        for (letter in list) {
            grid.find { it.id == letter.id }?.isSelected = true
        }
        updateState(grid = grid.chunked(4))
    }

    private fun checkIsWin() {
        if (_state.value.points <= 0) {
            onWin()
            updateState(isNavigate = true, percent = 100f)
            newSound(type = SoundType.IDLE)
        } else {
            newSound(type = SoundType.CORRECT_SWIPE)
        }
    }

    private fun resetGrid() {
        val grid = bB.getBoard()
        for (pos in selectedPath) {
            val letter = grid[pos.first][pos.second]
            grid[pos.first][pos.second] =
                createLetter(letter = letter, isSelected = false, isSwiped = false, isWrong = false)
        }
        updateState(grid = grid)
        selectedPath = ArrayList()
    }

    private fun updateState(
        grid: List<List<Letter>>? = null,
        percent: Float? = null,
        time: String? = null,
        points: Int? = null,
        stars: Int? = null,
        isNavigate: Boolean? = null,
        remainingAnswers: List<String>? = null,
        falehFeel: FalehFeel? = null,
    ) {
        _state.value = BoardState(
            grid = grid ?: _state.value.copy().grid,
            percent = percent ?: _state.value.copy().percent,
            time = time ?: _state.value.copy().time,
            points = points ?: _state.value.copy().points,
            isNavigate = isNavigate ?: _state.value.copy().isNavigate,
            stars = stars ?: _state.value.copy().stars,
            remainingAnswers = remainingAnswers ?: _state.value.copy().remainingAnswers,
            falehFeel = falehFeel ?: _state.value.copy().falehFeel,
            id = generateRandomUUIDString()
        )
    }

    private fun createLetter(
        letter: Letter,
        isSelected: Boolean,
        isSwiped: Boolean,
        isWrong: Boolean = false
    ): Letter {
        return Letter(
            id = generateRandomUUIDString(),
            letter = letter.letter,
            isSelected = isSelected,
            isSwiped = isSwiped,
            isWrong = isWrong
        )
    }

    private fun newSound(type: SoundType) {
        _soundState.value = SoundState(
            id = generateRandomUUIDString(),
            soundState = type
        )

    }
}