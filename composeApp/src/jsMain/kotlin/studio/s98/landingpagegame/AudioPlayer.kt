package studio.s98.landingpagegame

import org.w3c.dom.Audio

actual class AudioPlayer actual constructor(fileName: String) {
    // We create the DOM Audio element directly
    private val audio = Audio(fileName)

    actual fun play() {
        // Reset time to 0 to allow rapid firing (e.g., repeated button clicks)
        audio.currentTime = 0.0
        audio.play()
    }

    actual fun release() {
        // In the browser, the Garbage Collector handles this mostly,
        // but we can pause to stop playback immediately if the screen closes.
        audio.pause()
        audio.src = ""
    }
}