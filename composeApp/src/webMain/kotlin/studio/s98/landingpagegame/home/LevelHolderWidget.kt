package studio.s98.landingpagegame.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import landingpagegame.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource
import landingpagegame.composeapp.generated.resources.level_holder

@Composable
fun LevelHolderWidget(level: String) {

    Box(contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(resource = Res.drawable.level_holder),
            contentDescription = "Level holder"
        )
        Text(
            text = level,
            modifier = Modifier.padding(top = 24.dp),
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 40.sp
        )
    }
}
