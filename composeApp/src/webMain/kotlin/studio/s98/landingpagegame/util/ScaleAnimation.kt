package studio.s98.landingpagegame.util

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import kotlinx.coroutines.delay

@Composable
fun ScaleAnimation(
    modifier: Modifier = Modifier,
    startScale: Float = 0f,
    endScale: Float = 1f,
    delayTime: Long,
    content: @Composable () -> Unit,
) {
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(delayTime) {
        delay(delayTime)
        isVisible = true
    }

    val scale by animateFloatAsState(
        targetValue = if (isVisible) endScale else startScale,
        animationSpec = spring(
            stiffness = 150f,
            dampingRatio = 0.7f
        ),
        label = "scale"
    )

    Box(
        modifier = modifier.scale(scale)
    ) {
        content()
    }
}
