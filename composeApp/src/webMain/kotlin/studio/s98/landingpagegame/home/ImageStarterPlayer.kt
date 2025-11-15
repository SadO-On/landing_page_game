package studio.s98.landingpagegame.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
private fun ImageStarterPlayer(
    modifier: Modifier
) {

    val value by rememberInfiniteTransition(label = "").animateValue(
        initialValue = 1,
        targetValue = 20,
        typeConverter = Int.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = duration, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )


//    Image(
//        painter = painterResource(id = getDrawableId(value, context, frameName = frameName,  context.packageName)),
//        contentDescription = null,
//        modifier = modifier
//    )
}