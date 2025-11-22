package studio.s98.landingpagegame.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import landingpagegame.composeapp.generated.resources.Res
import landingpagegame.composeapp.generated.resources.phrase_1
import landingpagegame.composeapp.generated.resources.phrase_2
import landingpagegame.composeapp.generated.resources.phrase_3
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import studio.s98.landingpagegame.BackButtonWidget
import studio.s98.landingpagegame.LottieAnimation
import studio.s98.landingpagegame.PrimaryButtonWidget
import studio.s98.landingpagegame.board.BoardTileWidget
import studio.s98.landingpagegame.models.Letter
import studio.s98.landingpagegame.util.ScaleAnimation
import studio.s98.landingpagegame.util.mainBackground

@Composable
fun ResultScreen(
    viewModel: ResultViewModel = viewModel { ResultViewModel() },
    isWin: Boolean,
    starCount: Int,
    missingWords: List<String>,
    onBackClicked: () -> Unit,
    onNextClicked: () -> Unit
) {

    val isShow = remember { mutableStateOf(false) }


    DisposableEffect(key1 = Unit) {
        onDispose {
            viewModel.releasePlayers()
        }
    }

    LaunchedEffect(key1 = Unit) {
        delay(500)
        viewModel.playSound(starCount)
    }

    ResultContent(
        isShow = isShow.value,
        isWin = isWin,
        phraseRes = getPhrasesRes(starCount),
        starsRes = getStarsRes(starCount),
        onBackClicked = {
            onBackClicked()
        },
        missingWords = missingWords,
        onNextClicked = {
            onNextClicked()
        },
        onDoneLottie = { isShow.value = true })
}

private fun getStarsRes(starCount: Int): String {
    return when (starCount) {
        1 -> "files/one_star.json"
        2 -> "files/two_star.json"
        3 -> "files/three_star.json"
        else -> "files/zero_star.json"
    }


}

private fun getPhrasesRes(starCount: Int): DrawableResource {
    return when (starCount) {
        1 -> Res.drawable.phrase_1
        2 -> Res.drawable.phrase_2
        3 -> Res.drawable.phrase_3
        else -> Res.drawable.phrase_1
    }
}

@Composable
private fun ResultContent(
    isShow: Boolean,
    isWin: Boolean,
    phraseRes: DrawableResource,
    starsRes: String,
    missingWords: List<String>,
    onBackClicked: () -> Unit,
    onDoneLottie: () -> Unit,
    onNextClicked: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(mainBackground),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BackButtonWidget {
            onBackClicked()
        }
        LottieAnimation(modifier = Modifier.size(300.dp), path = starsRes) {
            onDoneLottie()
        }



        if (isShow)
            ScaleAnimation(delayTime = 100) {
                Image(
                    painter = painterResource(resource = phraseRes),
                    modifier = Modifier.offset(y = (-80).dp),
                    contentDescription = ""
                )
            }

        if (isShow && !isWin)
            ScaleAnimation(delayTime = 300) {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    Column(
                        modifier = Modifier.padding(bottom = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        for (word in missingWords) {

                            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                                for (i in word.indices) {
                                    BoardTileWidget(
                                        modifier = Modifier.size(30.dp),
                                        isOdd = i % 2 == 0,
                                        letter = Letter(
                                            id = "",
                                            letter = word[i].toString(),
                                            isWrong = false,
                                            isSelected = false,
                                            isSwiped = false,
                                        ),
                                        isSelected = false,
                                        textSize = 16
                                    )
                                }
                            }
                        }
                    }
                }
            }

        if (isShow)
            Text(".", color = mainBackground, modifier = Modifier.weight(1f))

        if (isShow)
            ScaleAnimation(delayTime = 250) {
                PrimaryButtonWidget(text = "التالي") {
                    onNextClicked()
                }
            }
    }
}