package cat.itb.m78.exercices.Trivial

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import cat.itb.m78.exercices.Navigation.Destination
import kotlinx.serialization.Serializable

object Ubication {
    @Serializable
    data object MenuScreen
    @Serializable
    data object GameScreen
    @Serializable
    data object SettingsScreen
    @Serializable
    data class ResultScreen (val points: Int)
}

fun settingsScreen(){

}

@Composable
fun TrivialNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Ubication.MenuScreen) {
        composable<Ubication.MenuScreen> {
            menuScreen( goToGameScreen = { navController.navigate(Ubication.GameScreen) },
                goToSettingsScreen = { navController.navigate(Ubication.SettingsScreen) })
        }
        composable<Ubication.GameScreen> {
            gameScreen( goToResultScreen = { navController.navigate(Ubication.ResultScreen(it)) })
        }
        composable<Ubication.SettingsScreen> {
            settingsScreen()
        }
        composable<Ubication.ResultScreen> { backStack ->
            val points = backStack.toRoute<Ubication.ResultScreen>().points
            resultScreen( goToMenuScreen = { navController.navigate(Ubication.MenuScreen) }, points = points)
        }
    }
}