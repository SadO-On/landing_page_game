package studio.s98.landingpagegame.board

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import studio.s98.landingpagegame.LottieAnimation
import studio.s98.landingpagegame.PrimaryButtonWidget

@Composable
fun TutorialScreen(onDone: () -> Unit) {
    val step = remember {
        mutableIntStateOf(1)
    }
    Dialog(
        onDismissRequest = {},
        DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.weight(0.6f),
                contentAlignment = Alignment.Center
            ) {
                if (step.value == 1)
                    LottieAnimation(
                        modifier = Modifier.size(650.dp),
                        path = "files/tutorial_first.json"
                    ) {

                    }
                if (step.value == 2)
                    LottieAnimation(
                        modifier = Modifier.size(650.dp),
                        path = "files/tutorial_second.json"
                    ) {

                    }
                if (step.value == 3)
                    LottieAnimation(
                        modifier = Modifier.size(650.dp),
                        path = "files/tutorial_third.json"
                    ) {
                    }
            }


            Box(
                modifier = Modifier.weight(0.2f),
                contentAlignment = Alignment.Center
            ) {
                PrimaryButtonWidget(text = if (step.intValue == 3) "فهمت" else "التالي") {
                    if (step.intValue >= 3) {
                        onDone()
                    } else {
                        step.intValue += 1
                    }
                }
            }

        }
    }
}

//@Preview
//@Composable
//fun TutorialScreenPreview(){
//    TutorialScreen {
//
//    }
//}

