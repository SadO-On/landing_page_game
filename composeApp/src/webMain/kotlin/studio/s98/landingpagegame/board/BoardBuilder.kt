package studio.s98.landingpagegame.board

import studio.s98.landingpagegame.viewmodel.Word
import studio.s98.landingpagegame.models.Letter
import kotlin.random.Random

class BoardBuilder(private val rows: Int, private val columns: Int) {

    private var board = ArrayList<ArrayList<Letter>>()
    private var type =
        Orientation.VERTICAL

    fun getBoard(): ArrayList<ArrayList<Letter>> {
        return board
    }

    fun build(wordsList: ArrayList<Word>) {
        createEmptyBoard()
        populateCorrectWords(wordsList)
        generateRandomLetters()
    }

    private fun populateCorrectWords(wordsList: ArrayList<Word>) {
        var attempt = 0

        while (wordsList.isNotEmpty()) {
            if (attempt++ > 250) {
                systamaticWay(wordsList)
                attempt = 0
                return
            }

            val wordToAdd = wordsList.first().word

            when (type) {
                Orientation.VERTICAL -> {
                    canAddVertically(wordToAdd)?.let { position ->
                        addVertical(position, wordToAdd)
                        wordsList.removeAt(0)
                    }
                }

                Orientation.HORIZONTAL -> {
                    canAddHorizontally(wordToAdd)?.let { position ->
                        addHorizontally(position, wordToAdd)
                        wordsList.removeAt(0)
                    }
                }
            }

            attempt++
        }

    }


    private fun addVertical(position: Position, word: String) {
        type = Orientation.HORIZONTAL
        for (offset in word.indices) {
            board[offset + position.row][position.col].letter = word[offset].toString()
        }
    }

    private fun canAddVertically(word: String): Position? {
        val posX = Random.nextInt(0, rows)      // upper bound is exclusive
        val posY = Random.nextInt(0, columns)

        return try {
            if ((posX until posX + word.length).all {
                    board[it][posY].letter.isEmpty() ||
                            board[it][posY].letter == word[it - posX].toString()
                }
            ) {
                Position(posX, posY)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    private fun createEmptyBoard() {
        val emptyBoard = ArrayList<ArrayList<Letter>>()

        for (row in 0 until rows) {
            val line = ArrayList<Letter>()
            for (col in 0 until columns) {
                line.add(Letter(letter = ""))
            }
            emptyBoard.add(line)
        }
        board = emptyBoard
    }

    private fun generateRandomLetters() {

        val arabicAlphabet = "أبتثجحخدذرزسشصضطظعغفقكلمنهوييء"
        val random = Random
        for (row in 0 until rows) {
            for (col in 0 until columns) {
                if (board[row][col].letter.isBlank()) {
                    board[row][col] = Letter(
                        letter = arabicAlphabet[random.nextInt(arabicAlphabet.length)].toString()
                    )
                }
            }
        }
    }

    private fun addHorizontally(position: Position, word: String) {
        type = Orientation.VERTICAL
        for (offset in word.indices) {
            board[position.row][offset + position.col].letter = word[offset].toString()
        }
    }


    private fun canAddHorizontally(word: String): Position? {
        val posX = Random.nextInt(0, rows)
        val posY = Random.nextInt(0, columns)

        return try {
            if ((posY until posY + word.length).all {
                    board[posX][it].letter.isEmpty() ||
                            board[posX][it].letter == word[it - posY].toString()
                }
            ) {
                Position(posX, posY)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    private fun systamaticWay(wordsList: ArrayList<Word>) {
        val randomList = wordsList.shuffled()
        for (word in randomList) {
            if (!tryPlaceWord(board, word.word)) {
            }
        }
    }

    private fun tryPlaceWord(grid: ArrayList<ArrayList<Letter>>, word: String): Boolean {
        for (direction in listOf("horizontal", "vertical")) {
            for (x in grid.indices) {
                for (y in grid[0].indices) {
                    if (canPlaceWord(grid, word, x, y, direction)) {
                        populateWordInGrid(grid, word, x, y, direction)
                        return true
                    }
                }
            }
        }
        return false
    }

    private fun canPlaceWord(
        grid: ArrayList<ArrayList<Letter>>,
        word: String,
        startX: Int,
        startY: Int,
        direction: String
    ): Boolean {
        return when (direction) {
            "horizontal" -> startY + word.length <= grid[0].size && word.indices.all { i ->
                grid[startX][startY + i].letter == "" || grid[startX][startY + i].letter == word[i].toString()
            }

            "vertical" -> startX + word.length <= grid.size && word.indices.all { i ->
                grid[startX + i][startY].letter == "" || grid[startX + i][startY].letter == word[i].toString()
            }

            else -> false
        }
    }

    private fun populateWordInGrid(
        grid: ArrayList<ArrayList<Letter>>,
        word: String,
        startX: Int,
        startY: Int,
        direction: String
    ) {
        when (direction) {
            "horizontal" -> word.indices.forEach { i ->
                grid[startX][startY + i] = Letter(letter = word[i].toString())
            }

            "vertical" -> word.indices.forEach { i ->
                grid[startX + i][startY] = Letter(letter = word[i].toString())
            }
        }
    }
}


enum class Orientation {
    VERTICAL,
    HORIZONTAL,
}

data class Position(val row: Int, val col: Int)
