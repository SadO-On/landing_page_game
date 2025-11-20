package studio.s98.landingpagegame

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import io.github.alexzhirkevich.compottie.LottieCompositionCache
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.animateLottieCompositionAsState
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
import landingpagegame.composeapp.generated.resources.Res

@Composable
fun LottieAnimation(modifier: Modifier, path: String, onFinish: () -> Unit) {
    val composition by rememberLottieComposition {
        LottieCompositionSpec.JsonString(
            Res.readBytes(path).decodeToString()
        )
    }
    val progress by animateLottieCompositionAsState(composition)

    Image(
        painter = rememberLottiePainter(
            composition = composition,
            iterations = 1,

            ),
        contentDescription = "Lottie animation",
        modifier = modifier
    )

    if (progress == 1.0f) {
        onFinish()
    }
}