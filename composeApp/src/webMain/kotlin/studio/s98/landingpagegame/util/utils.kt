package studio.s98.landingpagegame.util
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
fun generateRandomUUIDString(): String {
    val randomUuid = Uuid.random()
    return randomUuid.toString()
}
