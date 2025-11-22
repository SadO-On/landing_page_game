package studio.s98.landingpagegame.board

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TimerWidget(percent: Float, time: String) {
    val gradient = listOf(
        Color(0xFFF9F9F9),
        Color(0xFFF9F9F9),
        Color(0xFFEEE1F1),
        Color(0xFF6E3179),
    )

    Box(
        modifier = Modifier
            .padding(8.dp)
            .height(45.dp)
            .width(128.dp)
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
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
        ) {
            LinearProgressIndicator(
                progress = { percent / 100f },
                modifier = Modifier
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(10.dp)),
                color = Color(0xffB862D1),
                trackColor = Color.Transparent,
                strokeCap = StrokeCap.Butt,
            )

            Text(
                time,
                modifier = Modifier.offset(1.dp, 1.dp).align(Alignment.Center)
                    .padding(horizontal = 24.dp),
                color = Color.Black.copy(alpha = 0.6f),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                time,
                modifier = Modifier.align(Alignment.Center).padding(horizontal = 24.dp),
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


//@Preview
//@Composable
//private fun TimerWidgetPreview() {
//    TimerWidget(percent = 50f, time = "1:00")
//}