package cat.itb.m78.exercices.Trivial

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cat.itb.m78.exercices.Navigation.Destination
import cat.itb.m78.exercices.Navigation.GameViewModel
import kotlinx.serialization.Serializable
import m78exercices.composeapp.generated.resources.Audiowide_Regular
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.trivial
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import kotlin.random.Random

object Ubication {
    @Serializable
    data object MenuScreen
    @Serializable
    data object GameScreen
    @Serializable
    data object SettingsScreen
    @Serializable
    data class ResultScreen (val winner: String)
}

fun settingsScreen(){

}
fun resultScreen(){

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
            gameScreen()
        }
        composable<Ubication.SettingsScreen> {
            settingsScreen()
        }
        composable<Ubication.ResultScreen> {
            resultScreen()
        }
    }
}