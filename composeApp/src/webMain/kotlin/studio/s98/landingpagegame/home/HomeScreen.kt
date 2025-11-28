package studio.s98.landingpagegame.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import landingpagegame.composeapp.generated.resources.Res
import landingpagegame.composeapp.generated.resources.powered_by
import landingpagegame.composeapp.generated.resources.title
import org.jetbrains.compose.resources.painterResource
import studio.s98.landingpagegame.PrimaryButtonWidget
import studio.s98.landingpagegame.util.ScaleAnimation
import studio.s98.landingpagegame.util.mainBackground
import studio.s98.landingpagegame.viewmodel.UserLevel

@Composable
fun HomeScreen(
    onPlayClick: ()-> Unit
){
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
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 32.dp).fillMaxWidth().height(110.dp),
                contentDescription = "Title"
            )
        }
        ImageStarterPlayer(modifier = Modifier.scale(1.5f))



            Spacer(modifier = Modifier.weight(1f))
        ScaleAnimation(delayTime = 100) {

            PrimaryButtonWidget(text = "العب") {
                onPlayClick()
            }
        }
        ScaleAnimation(delayTime = 600) {
            Image(
                painter = painterResource(resource = Res.drawable.powered_by),
                contentDescription = "98's Studio",
                modifier = Modifier
                    .padding(bottom = 16.dp, top = 8.dp)
            )
        }
    }
}