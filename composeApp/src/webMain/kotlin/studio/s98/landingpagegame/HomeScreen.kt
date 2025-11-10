package studio.s98.landingpagegame

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import landingpagegame.composeapp.generated.resources.Res
import landingpagegame.composeapp.generated.resources.title
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import studio.s98.landingpagegame.util.ScaleAnimation
import studio.s98.landingpagegame.util.mainBackground
import studio.s98.landingpagegame.viewmodel.UserLevel

@Composable
fun HomeScreen(){


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(mainBackground),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        ScaleAnimation(delayTime = 300) {
            LevelWidget(Modifier.padding(horizontal = 24.dp), UserLevel())
        }
        ScaleAnimation(delayTime = 400) {
            Image(
                painter = painterResource(resource = Res.drawable.title),
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 32.dp),
                contentDescription = "Title"
            )
        }
//        StarterVideoPlayer()
    }
}