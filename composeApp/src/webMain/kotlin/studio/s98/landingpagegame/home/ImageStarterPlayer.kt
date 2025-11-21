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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import landingpagegame.composeapp.generated.resources.Res
import landingpagegame.composeapp.generated.resources.start1
import landingpagegame.composeapp.generated.resources.start2
import landingpagegame.composeapp.generated.resources.start3
import landingpagegame.composeapp.generated.resources.start4
import landingpagegame.composeapp.generated.resources.start5
import landingpagegame.composeapp.generated.resources.start6
import landingpagegame.composeapp.generated.resources.start7
import landingpagegame.composeapp.generated.resources.start8
import landingpagegame.composeapp.generated.resources.start9
import landingpagegame.composeapp.generated.resources.start10
import landingpagegame.composeapp.generated.resources.start11
import landingpagegame.composeapp.generated.resources.start12
import landingpagegame.composeapp.generated.resources.start13
import landingpagegame.composeapp.generated.resources.start14
import landingpagegame.composeapp.generated.resources.start15
import landingpagegame.composeapp.generated.resources.start16
import landingpagegame.composeapp.generated.resources.start17
import landingpagegame.composeapp.generated.resources.start18
import landingpagegame.composeapp.generated.resources.start19
import landingpagegame.composeapp.generated.resources.start20
import landingpagegame.composeapp.generated.resources.start21
import landingpagegame.composeapp.generated.resources.start22
import landingpagegame.composeapp.generated.resources.start23
import landingpagegame.composeapp.generated.resources.start24
import landingpagegame.composeapp.generated.resources.start25
import landingpagegame.composeapp.generated.resources.start26
import landingpagegame.composeapp.generated.resources.start27
import landingpagegame.composeapp.generated.resources.start28
import landingpagegame.composeapp.generated.resources.start29
import landingpagegame.composeapp.generated.resources.start30
import landingpagegame.composeapp.generated.resources.start31
import landingpagegame.composeapp.generated.resources.start32
import landingpagegame.composeapp.generated.resources.start33
import landingpagegame.composeapp.generated.resources.start34
import landingpagegame.composeapp.generated.resources.start35
import landingpagegame.composeapp.generated.resources.start36
import landingpagegame.composeapp.generated.resources.start37
import landingpagegame.composeapp.generated.resources.start38
import landingpagegame.composeapp.generated.resources.start39
import landingpagegame.composeapp.generated.resources.start40
import landingpagegame.composeapp.generated.resources.start41
import landingpagegame.composeapp.generated.resources.start42
import landingpagegame.composeapp.generated.resources.start43
import landingpagegame.composeapp.generated.resources.start44
import landingpagegame.composeapp.generated.resources.start45
import landingpagegame.composeapp.generated.resources.start46
import landingpagegame.composeapp.generated.resources.start47
import landingpagegame.composeapp.generated.resources.start48
import landingpagegame.composeapp.generated.resources.start49
import landingpagegame.composeapp.generated.resources.start50
import landingpagegame.composeapp.generated.resources.start51
import landingpagegame.composeapp.generated.resources.start52
import landingpagegame.composeapp.generated.resources.start53
import landingpagegame.composeapp.generated.resources.start54
import landingpagegame.composeapp.generated.resources.start55
import landingpagegame.composeapp.generated.resources.start56
import landingpagegame.composeapp.generated.resources.start57
import landingpagegame.composeapp.generated.resources.start58
import landingpagegame.composeapp.generated.resources.start59
import landingpagegame.composeapp.generated.resources.start60
import org.jetbrains.compose.resources.painterResource

@Composable
fun ImageStarterPlayer(
    modifier: Modifier = Modifier
) {
    val frames = remember {
        listOf(
            Res.drawable.start1,
            Res.drawable.start2,
            Res.drawable.start3,
            Res.drawable.start4,
            Res.drawable.start5,
            Res.drawable.start6,
            Res.drawable.start7,
            Res.drawable.start8,
            Res.drawable.start9,
            Res.drawable.start10,
            Res.drawable.start11,
            Res.drawable.start12,
            Res.drawable.start13,
            Res.drawable.start14,
            Res.drawable.start15,
            Res.drawable.start16,
            Res.drawable.start17,
            Res.drawable.start18,
            Res.drawable.start19,
            Res.drawable.start20,
            Res.drawable.start21,
            Res.drawable.start22,
            Res.drawable.start23,
            Res.drawable.start24,
            Res.drawable.start25,
            Res.drawable.start26,
            Res.drawable.start27,
            Res.drawable.start28,
            Res.drawable.start29,
            Res.drawable.start30,
            Res.drawable.start31,
            Res.drawable.start32,
            Res.drawable.start33,
            Res.drawable.start34,
            Res.drawable.start35,
            Res.drawable.start36,
            Res.drawable.start37,
            Res.drawable.start38,
            Res.drawable.start39,
            Res.drawable.start40,
            Res.drawable.start41,
            Res.drawable.start42,
            Res.drawable.start43,
            Res.drawable.start44,
            Res.drawable.start45,
            Res.drawable.start46,
            Res.drawable.start47,
            Res.drawable.start48,
            Res.drawable.start49,
            Res.drawable.start50,
            Res.drawable.start51,
            Res.drawable.start52,
            Res.drawable.start53,
            Res.drawable.start54,
            Res.drawable.start55,
            Res.drawable.start56,
            Res.drawable.start57,
            Res.drawable.start58,
            Res.drawable.start59,
            Res.drawable.start60,
        )
    }
    val painters = frames.map {
        painterResource(it)
    }
    val value by rememberInfiniteTransition(label = "").animateValue(
        initialValue = 0,
        targetValue = painters.lastIndex, // Animate to the list's last index
        typeConverter = Int.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "frameIndex"
    )

    if (painters.isNotEmpty()) {
        Image(
            painter = painters[value],
            contentDescription = null,
            modifier = modifier
        )
    }
}
