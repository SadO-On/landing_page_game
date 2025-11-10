package studio.s98.landingpagegame.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class Timer(private val time: Long, val onTick: () -> Unit){

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Default + job)
    private var timer: Job? =null

    private fun startCoroutineTimer(delayMillis: Long = 0, repeatMillis: Long = 0, action: () -> Unit) =
        scope.launch(Dispatchers.Default) {
            delay(delayMillis)
            if (repeatMillis > 0) {
                while (isActive) {
                    action()
                    delay(delayMillis)
                }
            } else {
                action()
            }
        }
    fun startTimer(){
        timer = startCoroutineTimer(delayMillis = 1000, repeatMillis = time) {
            scope.launch(Dispatchers.Main) {
                this@Timer.onTick()
            }
        }
        timer?.start()
    }

    fun cancelTimer(){
        timer?.cancel()
    }
}