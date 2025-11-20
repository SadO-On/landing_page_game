package studio.s98.landingpagegame.board

import studio.s98.landingpagegame.util.generateRandomUUIDString

data class SoundState(
    val id: String = generateRandomUUIDString(),
    var soundState: SoundType = SoundType.IDLE

)

enum class SoundType {
    STARTED, CORRECT_SWIPE, WRONG_SWIPE, HALF_TIME, IDLE
}