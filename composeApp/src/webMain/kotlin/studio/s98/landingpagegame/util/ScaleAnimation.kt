package studio.s98.landingpagegame.util

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import kotlinx.coroutines.delay

@Composable
fun ScaleAnimation(
    modifier: Modifier = Modifier,
    startScale: Float = 0.0f,
    endScale: Float = 1.0f,
    delayTime: Long,
    content: @Composable () -> Unit,
) {
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = true) {
        delay(delayTime)
        isVisible = true
    }

    val transition = updateTransition(targetState = isVisible, label = "")
    val scale by transition.animateFloat(
        transitionSpec = {
            if (targetState) {
                spring(
                    stiffness = 150f, // Adjust stiffness as per your need
                    dampingRatio = 0.7f // Adjust dampingRatio as per your need
                )
            } else {
                spring(
                    stiffness = 150f, // Adjust stiffness as per your need
                    dampingRatio = 0.7f // Adjust dampingRatio as per your need
                )
            }
        }, label = ""
    ) { visible ->
        if (visible) endScale else startScale
    }

    Box(
        modifier = modifier
            .graphicsLayer(scaleX = scale, scaleY = scale)
    ) {
        content()
    }
}
