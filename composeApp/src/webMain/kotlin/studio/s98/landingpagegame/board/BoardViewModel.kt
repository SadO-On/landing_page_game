package studio.s98.landingpagegame.board

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import studio.s98.landingpagegame.AudioPlayer
import studio.s98.landingpagegame.data.MainRepositoryImp
import studio.s98.landingpagegame.util.Timer
import studio.s98.landingpagegame.util.formatMillisecondsToMinutes
import studio.s98.landingpagegame.util.generateRandomUUIDString
import studio.s98.landingpagegame.viewmodel.Word
import studio.s98.landingpagegame.models.Letter

class BoardViewModel() : ViewModel() {

    // Simple logger so it's easy to filter in console
    private fun log(msg: String) {
        println("BoardVM ▶ $msg")
    }

    private val _state = MutableStateFlow(BoardState())
    val state = _state.asStateFlow()

    private val _soundState = MutableStateFlow(SoundState())

    private var timer: Timer? = null
    private var currentTime = 0L
    private val repository = MainRepositoryImp()
    private var selectedPath = ArrayList<Pair<Int, Int>>()

    //    private var totalTime: Long = 120000
    private var totalTime: Long = 120000
    private var bB: BoardBuilder = BoardBuilder(rows = 5, columns = 4)

    private val startSound = AudioPlayer("sounds/new_board.wav")
    private val correctSound = AudioPlayer("sounds/correct_swipe.wav")
    private val wrongSound = AudioPlayer("sounds/wrong_swipe.wav")
    private val almostSound = AudioPlayer("sounds/almost.wav")


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
        bB.build(ArrayList(wordsList))

        val list = ArrayList<String>()
        for (letter in wordsList) {
            list.add(letter.word)
        }

        val board = bB.getBoard()

        updateState(
            grid = board,
            points = wordsList.size * 100,
            isNavigate = false,
            stars = null,
            remainingAnswers = list,
        )
        newSound(type = SoundType.STARTED) // Here should enable the UI to load
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
        if (isOnlyHorizontalAndVertical()) {
            return
        }

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
            resetSwiped(x)
        } else {
            clearSwiped(x)
        }
    }

    private fun isOnlyHorizontalAndVertical(): Boolean {
        if (selectedPath.isNotEmpty()) {
            val isHorizontal = selectedPath.all { it.first == selectedPath.first().first }
            val isVertical = selectedPath.all { it.second == selectedPath.first().second }

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
        viewModelScope.launch {
            delay(500)
            resetSwiped(list)
        }
    }

    private fun clearSwiped() {
        wrongSwiped()
        viewModelScope.launch {
            delay(500)
            resetSwiped()
        }
    }

    private fun wrongSwiped(list: List<Letter>) {
        newSound(type = SoundType.WRONG_SWIPE)

        val idsToMark = list.map { it.id }.toSet()
        val currentGrid = _state.value.grid

        val newGrid = currentGrid.map { row ->
            row.map { letter ->
                if (letter.id in idsToMark) {
                    // create a NEW instance with isWrong = true
                    createLetter(
                        letter = letter,
                        isSelected = letter.isSelected,
                        isSwiped = letter.isSwiped,
                        isWrong = true
                    )
                } else {
                    letter
                }
            }
        }


        // if BoardBuilder must stay in sync, update it explicitly
        // bB.setBoard(newGrid)   // (add this method to BoardBuilder if needed)

        updateState(grid = newGrid)
    }

    private fun wrongSwiped() {
        log("wrongSwiped function Called")
        val currentGrid = _state.value.grid

        val newGrid = currentGrid.mapIndexed { rowIndex, row ->
            row.mapIndexed { colIndex, letter ->
                if (selectedPath.contains(Pair(rowIndex, colIndex))) {
                    createLetter(
                        letter = letter,
                        isSelected = letter.isSelected,
                        isSwiped = false,
                        isWrong = true
                    )
                } else {
                    letter
                }
            }
        }

        updateState(grid = newGrid)
    }

    private fun removeWord(selectedWord: String) {
        val temp = _state.value.remainingAnswers.toMutableList()
        temp.remove(selectedWord.reversed())
        temp.remove(selectedWord)

        updateState(remainingAnswers = temp)
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
        val idsToReset = list.map { it.id }.toSet()
        val currentGrid = _state.value.grid

        val newGrid = currentGrid.map { row ->
            row.map { letter ->
                if (letter.id in idsToReset) {
                    createLetter(
                        letter = letter,
                        isSelected = letter.isSelected,
                        isSwiped = letter.isSwiped,
                        isWrong = false
                    )
                } else {
                    letter
                }
            }
        }


        // bB.setBoard(newGrid)   // if you want BoardBuilder synchronized

        updateState(grid = newGrid)
    }

    private fun createPath(positions: List<Int>) {
        val pos = Pair(positions[0], positions[1])
        if (!selectedPath.contains(pos)) {
            val grid = bB.getBoard()
            val letter = grid[positions[0]][positions[1]]
            grid[positions[0]][positions[1]] =
                createLetter(letter = letter, isSelected = letter.isSelected, isSwiped = true)
            selectedPath.add(pos)
            updateState(grid = grid)
        } else {
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

    override fun onCleared() {
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
        val timeLeftPercentage = ((currentTime).toDouble() / totalTime) * 100
        val stars = when {
            timeLeftPercentage > 75 -> 3
            timeLeftPercentage > 50 -> 2
            timeLeftPercentage < 50 -> 1
            timeLeftPercentage <= 0 -> 0
            else -> 0
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
        val newGrid = grid ?: _state.value.grid
        val wrongCount = newGrid.flatten().count { it.isWrong }

        _state.value = BoardState(
            grid = newGrid,
            percent = percent ?: _state.value.percent,
            time = time ?: _state.value.time,
            points = points ?: _state.value.points,
            isNavigate = isNavigate ?: _state.value.isNavigate,
            stars = stars ?: _state.value.stars,
            remainingAnswers = remainingAnswers ?: _state.value.remainingAnswers,
            falehFeel = falehFeel ?: _state.value.falehFeel,
            id = generateRandomUUIDString()
        )
    }

    private fun createLetter(
        letter: Letter,
        isSelected: Boolean,
        isSwiped: Boolean,
        isWrong: Boolean = false
    ): Letter {
        return letter.copy(
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

    fun soundStateListener() {
        viewModelScope.launch {
            _soundState.collect {
                withContext(Dispatchers.Default) {
                    when (_soundState.value.soundState) {
                        SoundType.STARTED -> {
                            startSound.play()
                        }

                        SoundType.CORRECT_SWIPE -> {
                            correctSound.play()
                        }

                        SoundType.WRONG_SWIPE -> {
                            log("wrongSound")
                            wrongSound.play() // No play
                        }

                        SoundType.HALF_TIME -> {
                            almostSound.play()
                        }

                        SoundType.IDLE -> {}
                    }
                }
            }
        }
    }

    fun release(){
        startSound.release()
        correctSound.release()
        wrongSound.release()
        almostSound.release()
    }
}
