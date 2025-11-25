package studio.s98.landingpagegame.board

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview
import studio.s98.landingpagegame.models.Letter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp

@Composable
fun BoardTileWidget(
    modifier: Modifier,
    isOdd: Boolean,
    letter: Letter,
    isSelected: Boolean,
    textSize: Int
) {
    val screenWidthDp = getScreenWidthDp()
    val animatedColor = animateColorAsState(
        targetValue = getColor(isOdd = isOdd, letter = letter),
        animationSpec = tween(250, 0, LinearEasing), label = ""
    )

    Box(
        modifier = modifier
            .background(
                color = animatedColor.value,
                shape = RoundedCornerShape(6.dp)
            )
            .size((screenWidthDp.value * 0.12).dp)
            .animateContentSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = letter.letter,
            fontSize = if (isSelected) (textSize + 6).sp else textSize.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White,
            modifier = Modifier.animateContentSize()
        )
    }

}

@Composable
private fun getColor(isOdd: Boolean, letter: Letter): Color {
    val colorHex = if (letter.isWrong) {
        0xffDF2EAD
    } else if (letter.isSelected) {
        0xff5CD5E5
    } else if (isOdd) {
        0xff9C51B9
    } else {
        0xffD57DF8
    }
    return Color(colorHex)
}


@Preview
@Composable
private fun BoardTileWidgetPreview() {
    BoardTileWidget(
        modifier = Modifier, isOdd = false, letter = Letter(
            id = "",
            letter = "ب",
            isWrong = false,
            isSelected = false,
            isSwiped = false,
        ),
        isSelected = false,
        textSize = 30
    )
}



@Composable
fun getScreenWidthDp(): Dp {
    val density = LocalDensity.current

    val windowSize = LocalWindowInfo.current.containerSize

    val screenWidthPx = windowSize.width

    return with(density) { screenWidthPx.toDp() }
}