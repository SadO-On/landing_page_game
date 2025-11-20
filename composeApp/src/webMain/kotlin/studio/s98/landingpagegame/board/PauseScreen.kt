package studio.s98.landingpagegame.board


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import landingpagegame.composeapp.generated.resources.Res
import landingpagegame.composeapp.generated.resources.resume
import org.jetbrains.compose.resources.painterResource
import studio.s98.landingpagegame.PrimaryButtonWidget
import studio.s98.landingpagegame.util.ScaleAnimation


@Composable
fun PauseScreen(
    onBackToHome: () -> Unit,
    onDismiss: () -> Unit
) {


    PauseScreenContent(onBackToHome = {
        onBackToHome()
    }) {
        onDismiss()
    }

}

@Composable
private fun PauseScreenContent(
    onBackToHome: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = {},
        DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Box {}
            ScaleAnimation(delayTime = 300) {
                Image(
                    painter = painterResource(resource = Res.drawable.resume),
                    contentDescription = "أكمل اللعب",
                    modifier = Modifier
                        .size(96.dp)
                        .clickable {
                            onDismiss()
                        })
            }
            ScaleAnimation(delayTime = 500) {
                PrimaryButtonWidget(text = "العودة للرئيسية") {
                    onBackToHome()
                }
            }

        }

    }
}

