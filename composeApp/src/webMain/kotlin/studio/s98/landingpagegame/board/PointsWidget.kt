package studio.s98.landingpagegame.board

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PointsWidget(point: Int){
    val gradient = listOf(
        Color(0xFF9D52BA),
        Color(0xFF9D52BA),
        Color(0xFF984FB3),
        Color(0xFF944CAD),
    )
    Box(
        modifier = Modifier
            .padding(8.dp)
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
            ), contentAlignment = Alignment.Center
    ){
        Text(
            text = "$point نقطة",
            modifier = Modifier.padding(horizontal = 24.dp),
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize =  20.sp,
            style = TextStyle(textDirection = TextDirection.Rtl)
        )
    }
}

@Preview
@Composable
private fun PointsWidgetPreview(){
    PointsWidget(point = 500)
}