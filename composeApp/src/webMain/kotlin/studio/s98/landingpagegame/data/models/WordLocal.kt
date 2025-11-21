package studio.s98.landingpagegame.data.models
import studio.s98.landingpagegame.util.generateRandomUUIDString

data class WordLocal(
    val id: String = generateRandomUUIDString(),
    val word: String
)