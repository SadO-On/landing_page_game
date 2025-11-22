package studio.s98.landingpagegame

import org.w3c.dom.Audio

actual class AudioPlayer actual constructor(fileName: String) {
    private val audio = Audio(fileName)

    @OptIn(ExperimentalWasmJsInterop::class)
    actual fun play() {
        audio.currentTime = 0.0
        audio.play()
    }

    actual fun release() {
        audio.pause()
        audio.src = ""
    }
}