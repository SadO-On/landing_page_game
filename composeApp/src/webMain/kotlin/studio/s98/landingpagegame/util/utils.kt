package studio.s98.landingpagegame.util
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
fun generateRandomUUIDString(): String {
    val randomUuid = Uuid.random()
    return randomUuid.toString()
}


fun formatMillisecondsToMinutes(milliseconds: Long): String {
    val totalSeconds = milliseconds / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "${minutes}:${seconds}"
}
