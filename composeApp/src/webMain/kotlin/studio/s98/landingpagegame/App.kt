package studio.s98.landingpagegame

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import studio.s98.landingpagegame.board.BoardScreen
import studio.s98.landingpagegame.home.HomeScreen

@Composable
fun App() {
    MaterialTheme(
        typography = TajwalTypography()
    ) {
        MainNavGraph()
    }
}

@Composable
fun MainNavGraph(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    NavHost(
        navController = navController,
        startDestination = Routes.Title.name
    ) {
        composable(route = Routes.Title.name) {
            HomeScreen {
                navController.navigate(Routes.Board.name)
            }
        }
        composable(route = Routes.Board.name) {
            BoardScreen(isFirst = false, toResult = { _, _ -> }, onBackToHome = {
                navController.navigateUp()
            })
        }
    }
}


//  HomeScreen()