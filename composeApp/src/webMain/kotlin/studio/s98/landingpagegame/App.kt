package studio.s98.landingpagegame

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import studio.s98.landingpagegame.board.BoardScreen
import studio.s98.landingpagegame.home.HomeScreen
import studio.s98.landingpagegame.result.ResultScreen

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
    NavHost(
        navController = navController,
        startDestination = Title
    ) {
        composable<Title> {
            HomeScreen {
                navController.navigate(Board)
            }
        }
        composable<Board> {
            BoardScreen(isFirst = true, toResult = { star, list ->
                navController.navigate(Result(star, list)){
                    popUpTo(Title){
                        inclusive = false
                    }
                }

            }, onBackToHome = {
                navController.navigateUp()
            })
        }

        composable<Result> { entry ->
            val result: Result = entry.toRoute()
            ResultScreen(
                isWin = result.starsCount != 0,
                starCount = result.starsCount,
                missingWords = result.missingWords,
                onNextClicked = {
                    navController.navigate(Board){
                        popUpTo(Title){
                            inclusive = false
                        }
                    }
                },
                onBackClicked = {
                    navController.popBackStack(
                        route = Title,
                        inclusive = false
                    )
                }
            )
        }
    }
}

/****
 * TODO remaining bugs:
 * 1- Back to game ✅
 * 2- Wierd . in the timer ✅
 * 3- Wrong swipe sound not trigger ✅
 * 4- Performance enhancements
 * 5- Wrong Freeze In the 00:52
 */
