package studio.s98.landingpagegame.viewmodel

import studio.s98.landingpagegame.util.generateRandomUUIDString

data class Word(
    val id: String = generateRandomUUIDString(),
    val word: String
)
