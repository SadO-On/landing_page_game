package studio.s98.landingpagegame.viewmodel

data class UserLevel(
    val level: Int = 1,
    val xp: Float  = 0f,
    var isFirstTime: Boolean = true
)