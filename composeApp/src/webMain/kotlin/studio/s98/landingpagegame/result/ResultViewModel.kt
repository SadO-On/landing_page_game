package studio.s98.landingpagegame.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import studio.s98.landingpagegame.AudioPlayer


class ResultViewModel : ViewModel() {
    private val zero = AudioPlayer("sounds/zero_star_sound.wav")
    private val one = AudioPlayer("sounds/one_star_sound.wav")
    private val two = AudioPlayer("sounds/two_star_sound.wav")
    private val three = AudioPlayer("sounds/three_star_sound.wav")

    fun playSound(startCount: Int) {
        viewModelScope.launch {
            when (startCount) {
                0 -> zero.play()
                1 -> one.play()
                2 -> two.play()
                3 -> three.play()
            }
        }
    }

    fun releasePlayers() {
        zero.release()
        one.release()
        two.release()
        three.release()
    }
}