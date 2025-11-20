package studio.s98.landingpagegame.models

import studio.s98.landingpagegame.util.generateRandomUUIDString



data class Letter(
    val id: String = generateRandomUUIDString(),
    var letter: String,
    var isSwiped: Boolean = false,
    var isSelected: Boolean = false,
    var isWrong: Boolean = false
)
