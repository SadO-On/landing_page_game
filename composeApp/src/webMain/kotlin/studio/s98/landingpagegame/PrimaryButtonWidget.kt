package studio.s98.landingpagegame

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import landingpagegame.composeapp.generated.resources.Res
import landingpagegame.composeapp.generated.resources.play_button
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PrimaryButtonWidget(text: String, onClick: () -> Unit) {
    val soundPlayer = remember { AudioPlayer("sounds/click.wav") }

    DisposableEffect(soundPlayer) {
        onDispose {
            soundPlayer.release()
        }
    }
    Box(contentAlignment = Alignment.Center, modifier = Modifier.clickable {

        onClick()
        soundPlayer.play()
    }) {
        Image(
            painter = painterResource(resource =  Res.drawable.play_button),
            contentDescription = "play button",
            modifier = Modifier.height(120.dp)
        )
        Text(
            text = text,
            modifier = Modifier.padding(bottom = 12.dp),
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 40.sp
        )
    }

}


@Preview
@Composable
private fun PrimaryButtonWidgetPreview() {
    PrimaryButtonWidget(text = "إلعب") {

    }
}