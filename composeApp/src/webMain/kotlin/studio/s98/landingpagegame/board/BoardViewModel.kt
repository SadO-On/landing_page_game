package studio.s98.landingpagegame.board

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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
    private var totalTime: Long = 120000
    private var bB: BoardBuilder = BoardBuilder(rows = 5, columns = 4)

    fun onEvent(event: BoardEvents) {
        log("onEvent: $event")
        when (event) {
            is BoardEvents.GameStarted -> {
                initGame()
            }

            is BoardEvents.UserSwiped -> {
                log("onEvent UserSwiped -> checkSwiped()")
                checkSwiped()
            }

            is BoardEvents.LetterSwiped -> {
                log("onEvent LetterSwiped -> createPath(${event.positions})")
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
                log("onEvent LetterSwipedWithLetterInformation -> checkSwiped(letters=${event.letter})")
                checkSwiped(event.letter)
            }
        }
    }

    private fun initGame() {
        log("initGame()")
        setDifficulty()
    }

    private fun startGame(wordsList: List<Word> = repository.generateNewWords(3)) {
        log("startGame() with wordsList=${wordsList.map { it.word }}")
        launchTimer(isResume = false)
        bB.build(ArrayList(wordsList))

        val list = ArrayList<String>()
        for (letter in wordsList) {
            list.add(letter.word)
        }

        val board = bB.getBoard()
        log("startGame() -> board size=${board.size}x${board.firstOrNull()?.size ?: 0}")

        updateState(
            grid = board,
            points = wordsList.size * 100,
            isNavigate = false,
            stars = null,
            remainingAnswers = list,
        )
        newSound(type = SoundType.STARTED)
    }

    private fun setDifficulty() {
        val level = repository.getUserLevel()
        log("setDifficulty() -> level=${level.level}")
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
        log("checkSwiped() using selectedPath=$selectedPath")
        if (isOnlyHorizontalAndVertical()) {
            log("checkSwiped() -> path is not valid (not only H/V), returning")
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

        log("checkSwiped() -> selectedWord='$selectedWord', userFoundTheWord=$userFoundTheWord")

        if (!userFoundTheWord && repository.isContained(selectedWord.trim())) {
            log("checkSwiped() -> CORRECT word")
            countPoints()
            disableTile()
            removeWord(selectedWord)
            checkIsWin()
            log("checkSwiped() -> calling resetSwiped()")
            resetSwiped()
        } else {
            log("checkSwiped() -> WRONG word, calling clearSwiped()")
            clearSwiped()
        }
    }

    private fun checkSwiped(letters: Set<Int>) {
        log("checkSwiped(letters=$letters)")
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

        log("checkSwiped(list) -> selectedWord='$selectedWord', userFoundTheWord=$userFoundTheWord")

        if (!userFoundTheWord && repository.isContained(selectedWord.trim())) {
            log("checkSwiped(list) -> CORRECT word")
            countPoints()
            disableTile(x)
            removeWord(selectedWord)
            checkIsWin()
            log("checkSwiped(list) -> calling resetSwiped(list)")
            resetSwiped(x)
        } else {
            log("checkSwiped(list) -> WRONG word, calling clearSwiped(list)")
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

            log(
                "isOnlyHorizontalAndVertical(): " +
                        "horizontal=$isHorizontal, vertical=$isVertical, " +
                        "seqH=$isSequentialHorizontal, seqV=$isSequentialVertical"
            )

            if (!(isHorizontal || isVertical) || !(isSequentialHorizontal || isSequentialVertical)) {
                log("isOnlyHorizontalAndVertical() -> INVALID path, calling clearSwiped()")
                clearSwiped()
                return true
            }
        }
        return false
    }

    private fun clearSwiped(list: List<Letter>) {
        log("clearSwiped(list) called with ids=${list.map { it.id }}")
        wrongSwiped(list)
        viewModelScope.launch {
            log("clearSwiped(list) -> delay(1000)")
            delay(1000)
            log("clearSwiped(list) -> calling resetSwiped(list)")
            resetSwiped(list)
        }
    }

    private fun clearSwiped() {
        log("clearSwiped() called with selectedPath=$selectedPath")
        wrongSwiped()
        viewModelScope.launch {
            log("clearSwiped() -> delay(1000)")
            delay(1000)
            log("clearSwiped() -> calling resetSwiped()")
            resetSwiped()
        }
    }

    private fun wrongSwiped(list: List<Letter>) {
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

        log("wrongSwiped(list) -> new wrongCount=${newGrid.flatten().count { it.isWrong }}")

        // if BoardBuilder must stay in sync, update it explicitly
        // bB.setBoard(newGrid)   // (add this method to BoardBuilder if needed)

        updateState(grid = newGrid)
    }

    private fun wrongSwiped() {
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
        newSound(type = SoundType.WRONG_SWIPE)
    }

    private fun removeWord(selectedWord: String) {
        val temp = _state.value.remainingAnswers.toMutableList()
        temp.remove(selectedWord.reversed())
        temp.remove(selectedWord)

        updateState(remainingAnswers = temp)
    }


    private fun resetSwiped() {
        log("resetSwiped() with selectedPath=$selectedPath")
        val grid = bB.getBoard()
        log("resetSwiped() BEFORE -> wrongCount=${grid.flatten().count { it.isWrong }}")
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
        log("resetSwiped() AFTER  -> wrongCount=${grid.flatten().count { it.isWrong }}")
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

        log("resetSwiped(list) -> new wrongCount=${newGrid.flatten().count { it.isWrong }}")

        // bB.setBoard(newGrid)   // if you want BoardBuilder synchronized

        updateState(grid = newGrid)
    }

    private fun createPath(positions: List<Int>) {
        log("createPath(positions=$positions)")
        val pos = Pair(positions[0], positions[1])
        if (!selectedPath.contains(pos)) {
            val grid = bB.getBoard()
            val letter = grid[positions[0]][positions[1]]
            grid[positions[0]][positions[1]] =
                createLetter(letter = letter, isSelected = letter.isSelected, isSwiped = true)
            selectedPath.add(pos)
            log("createPath() -> selectedPath=$selectedPath")
            updateState(grid = grid)
        } else {
            log("createPath() -> pos already in selectedPath, ignoring")
        }
    }

    private fun launchTimer(isResume: Boolean) {
        currentTime = if (isResume) currentTime else totalTime
        log("launchTimer(isResume=$isResume) starting with currentTime=$currentTime totalTime=$totalTime")
        var isHalfTime = true
        timer = Timer(totalTime) {
            if (currentTime < 0) {
                log("Timer tick -> time finished")
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
        log("onPause()")
        timer?.cancelTimer()
    }

    private fun onResume() {
        log("onResume()")
        launchTimer(true)
    }

    override fun onCleared() {
        log("onCleared()")
        timer?.cancelTimer()
        resetGrid()
    }

    private fun onTimeFinished() {
        log("onTimeFinished()")
        updateState(isNavigate = true, stars = 0, percent = 100f)
        newSound(type = SoundType.IDLE)
        onCleared()
    }

    private fun onWin() {
        log("onWin()")
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
        log("calculateStars() -> timeLeftPercentage=$timeLeftPercentage, stars=$stars")
        return stars
    }

    private fun addXP(stars: Int) {
        log("addXP(stars=$stars)")
        repository.addXP(stars)
    }

    private fun countPoints() {
        val newPoints = _state.value.points - 100
        log("countPoints() -> old=${_state.value.points}, new=$newPoints")
        updateState(points = newPoints, falehFeel = FalehFeel.CORRECT)
    }

    private fun disableTile() {
        log("disableTile() with selectedPath=$selectedPath")
        val grid = bB.getBoard()
        for (pos in selectedPath) {
            val letter = grid[pos.first][pos.second]
            grid[pos.first][pos.second] =
                createLetter(letter = letter, isSelected = true, isSwiped = false)
        }
        updateState(grid = grid)
    }

    private fun disableTile(list: List<Letter>) {
        log("disableTile(list) ids=${list.map { it.id }}")
        val grid = bB.getBoard().flatten()
        for (letter in list) {
            grid.find { it.id == letter.id }?.isSelected = true
        }
        updateState(grid = grid.chunked(4))
    }

    private fun checkIsWin() {
        log("checkIsWin() -> points=${_state.value.points}")
        if (_state.value.points <= 0) {
            log("checkIsWin() -> WIN")
            onWin()
            updateState(isNavigate = true, percent = 100f)
            newSound(type = SoundType.IDLE)
        } else {
            log("checkIsWin() -> NOT win yet")
            newSound(type = SoundType.CORRECT_SWIPE)
        }
    }

    private fun resetGrid() {
        log("resetGrid() with selectedPath=$selectedPath")
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
        log(
            "updateState() -> " +
                    "points=${points ?: _state.value.points}, " +
                    "time=${time ?: _state.value.time}, " +
                    "percent=${percent ?: _state.value.percent}, " +
                    "isNavigate=${isNavigate ?: _state.value.isNavigate}, " +
                    "wrongCount=$wrongCount"
        )

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
        log("newSound(type=$type)")
        _soundState.value = SoundState(
            id = generateRandomUUIDString(),
            soundState = type
        )
    }
}
