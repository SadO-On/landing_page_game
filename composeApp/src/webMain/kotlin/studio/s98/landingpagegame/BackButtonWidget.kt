package studio.s98.landingpagegame

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import landingpagegame.composeapp.generated.resources.Res
import landingpagegame.composeapp.generated.resources.back_btn
import org.jetbrains.compose.resources.painterResource



@Composable
fun BackButtonWidget(onClick: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        Image(
            modifier = Modifier.clickable {
                onClick()
            }.size(56.dp),
            painter = painterResource(Res.drawable.back_btn),
            contentDescription = null,
        )
    }
}

