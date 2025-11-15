package studio.s98.landingpagegame

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import studio.s98.landingpagegame.home.HomeScreen

@Composable
fun App() {
    MaterialTheme {
        HomeScreen()
    }
}