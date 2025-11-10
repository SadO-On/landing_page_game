package studio.s98.landingpagegame

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import studio.s98.landingpagegame.viewmodel.UserLevel

@Composable
fun LevelWidget(modifier: Modifier, userLevel: UserLevel) {
    LevelWidgetContent(modifier = modifier, level = userLevel)
}

@Composable
private fun LevelWidgetContent(modifier: Modifier, level: UserLevel) {
    Box(contentAlignment = Alignment.CenterStart, modifier = modifier) {
        Box(modifier = Modifier.padding(start = 32.dp)) {
            LevelBarWidget(percent = level.xp)
        }
        LevelHolderWidget(level = if(level.level == -1 ) "1" else level.level.toString())
    }
}

@Preview
@Composable
private fun LevelWidgetPreview() {
    LevelWidgetContent(
        modifier = Modifier,
        level = UserLevel(
            level = 4, xp = (40 / 100).toFloat(),
            isFirstTime = false
        )
    )
}