package studio.s98.landingpagegame

// src/commonMain/kotlin/AudioPlayer.kt

expect class AudioPlayer(fileName: String) {
    fun play()
    fun release() // Good practice to have cleanup capability
}