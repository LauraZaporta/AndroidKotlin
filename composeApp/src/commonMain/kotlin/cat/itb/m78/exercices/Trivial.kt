package cat.itb.m78.exercices

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import m78exercices.composeapp.generated.resources.Kanit_Light
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.Trivial
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

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

class TrivialVM : ViewModel(){

}

@Composable
fun menuScreen(){
    Column(modifier = Modifier.fillMaxSize() .background(Color(0XFF23B8CF)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){

        Text("Trivial", fontSize = 3.em, fontFamily = FontFamily(Font(Res.font.Kanit_Light)))
        Spacer(Modifier.height(30.dp))
        Image(painter = painterResource(Res.drawable.Trivial),
            contentDescription = null,
            modifier = Modifier.size(150.dp))
        Spacer(Modifier.height(20.dp))
        Button( onClick = {}){
            Text("New game")
        }
        Spacer(Modifier.height(10.dp))
        Button( onClick = {}){
            Text("Settings")
        }
    }
}
fun gameScreen(){

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
            menuScreen()
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