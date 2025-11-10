package studio.s98.landingpagegame.data

import studio.s98.landingpagegame.viewmodel.UserLevel
import studio.s98.landingpagegame.viewmodel.Word

interface MainRepository {
    fun generateNewWords(num: Int): List<Word>

    fun isContained(word: String): Boolean

    fun addXP(stars: Int)

    fun getUserLevel(): UserLevel
}