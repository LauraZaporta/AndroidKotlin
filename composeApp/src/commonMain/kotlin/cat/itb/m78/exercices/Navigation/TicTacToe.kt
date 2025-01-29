package cat.itb.m78.exercices.Navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

object DestinationTTT {
    @Serializable
    data object PlayScreen
    @Serializable
    data object GameScreen
    @Serializable
    data object EndScreen
}

fun List<List<Boolean?>>.toMutableMatrix(): List<MutableList<Boolean?>> {
    return map { it.toMutableList() }
}

private class TicTacToeView : ViewModel() {
    val winner = mutableStateOf<String>("")
    val XorO = mutableStateOf<Boolean?>(null)

    val GameMatrix = mutableListOf<

    fun winnerIsMessage(X : Boolean){
        if (X){
            winner.value = "X is the winner!"
        } else {
            winner.value = "O is the winner!"
        }
    }
    fun winnerIs(){
        XorO.value = false //MODIFICAR
    }
}

@Composable
fun PlayScreen(goToGameScreen : () -> Unit){
    Column(modifier = Modifier.fillMaxSize()
        .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Button(onClick = goToGameScreen){
            Text("Play", fontSize = 2.em, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun placeButton(){
    Spacer(Modifier.width(3.dp))
    Button(onClick = {}){
        Text("")
    }
    Spacer(Modifier.width(3.dp))
}
@Composable
fun placeRow(){
    Row(){
        placeButton()
        placeButton()
        placeButton()
    }
}

@Composable
fun GameScreen(addPoint : () -> Unit){
    Column(modifier = Modifier.fillMaxSize()){
        placeRow()
        placeRow()
        placeRow()
    }
}

@Composable
fun EndScreen(winnerMessage : String, replay: () -> Unit){
    Column(modifier = Modifier.fillMaxSize()
        .background(Color.Green),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Text(winnerMessage, fontSize = 3.em)
        Spacer(Modifier.height(20.dp))
        Button(onClick = replay){
            Text("Play again!", fontSize = 2.em, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun TikTok() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = DestinationTTT.PlayScreen) {
        composable<DestinationTTT.PlayScreen> {
            PlayScreen(
                goToGameScreen = { navController.navigate(DestinationTTT.GameScreen) },
            )
        }
        composable<Destination.Screen2> {
            Screen2 (
                navigateToMenuScreen = { navController.navigate(Destination.MenuScreen) }
            )
        }
        composable<Destination.Screen3> { backStack ->
            val message = backStack.toRoute<Destination.Screen3>().message
            Screen3(navigateToMenuScreen = { navController.navigate(Destination.MenuScreen) }, message)
        }
        composable<Destination.MenuScreen> {
            MenuScreen (
                navigateToScreen1 = { navController.navigate(Destination.Screen1) },
                navigateToScreen2 = { navController.navigate(Destination.Screen2) },
                navigateToScreen3H = { navController.navigate(Destination.Screen3("Hello")) },
                navigateToScreen3B = { navController.navigate(Destination.Screen3("Bye")) },
            )
        }
    }
}