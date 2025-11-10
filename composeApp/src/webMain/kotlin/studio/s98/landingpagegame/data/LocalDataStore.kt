package studio.s98.landingpagegame.data

import studio.s98.landingpagegame.data.models.WordLocal


class LocalDataStore(
//    private val context: SP
) {

    private var wordsList = listOf<WordLocal>()
    fun generateNewWords(num: Int): List<WordLocal> {
        wordsList = listOf(
            WordLocal(word = fourLetterArabicWords.random()),
            WordLocal(word = twoLetterArabicWords.random()),
            WordLocal(word = threeLetterArabicWords.random())
        )
        return ArrayList(
            wordsList
        ).shuffled().take(num)
    }

    fun isContained(word: String): Boolean {
        for (item in getDataSet())
            if (item == word)
                return true

        return false
    }

    fun getXP(): Int {
        return 0
//        return context.getInt(XP_KEY)
    }

    fun addXp(xp: Int) {
//        context.setInt(XP_KEY, xp)
    }

    fun getLevel(): Int {
        return 0
//        return context.getInt(LEVEL_KEY)
    }

    fun addLevel(level: Int) {
//        context.setInt(LEVEL_KEY, level)
    }

    private fun getDataSet(): List<String> {
        return fourLetterArabicWords + threeLetterArabicWords + twoLetterArabicWords
    }
}

const val XP_KEY = "xp"
const val LEVEL_KEY = "level"