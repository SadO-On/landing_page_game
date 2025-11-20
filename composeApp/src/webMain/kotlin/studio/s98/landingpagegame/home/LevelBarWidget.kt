package studio.s98.landingpagegame.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

@Composable
fun LevelBarWidget(percent: Float) {
    val gradient = listOf(
        Color(0xFFF9F9F9),
        Color(0xFFF9F9F9),
        Color(0xFFEEE1F1),
        Color(0xFF6E3179),
    )

    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(45.dp)
            .border(
                width = 2.dp,
                color = Color(0xff803E8F),
                shape = RoundedCornerShape(10.dp)
            )
            .background(
                brush = Brush.verticalGradient(
                    colors = gradient
                ),
                shape = RoundedCornerShape(10.dp)
            ),
    ) {
        LinearProgressIndicator(
            progress = { percent / 100f },
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp)),
            color = Color.Transparent,
            trackColor = Color(0xffB862D1),
            strokeCap = StrokeCap.Butt,
            )
    }
}
