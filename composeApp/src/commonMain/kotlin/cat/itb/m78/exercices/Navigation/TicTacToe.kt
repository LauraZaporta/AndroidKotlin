package cat.itb.m78.exercices.Navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

fun List<List<Boolean?>>.toMutableMatrix(): List<MutableList<Boolean?>> {
    return map { it.toMutableList() }
}

class GameViewModel : ViewModel() {
    // Totes les caselles null
    var tauler = mutableStateOf(List(3) { List<Boolean?>(3) { null as Boolean? } })

    var jugadorActual = mutableStateOf(true)
    var guanyador = mutableStateOf<String?>(null)

    // Funció per fer jugada
    fun doPlay(fila: Int, columna: Int) {
        if (tauler.value[fila][columna] == null && guanyador.value == null) {
            val newBoard = tauler.value.toMutableMatrix()
            newBoard[fila][columna] = if (jugadorActual.value) true else false
            tauler.value = newBoard

            // El jugador X posa caselles true i O false
            // Es modifica visualment el tauler

            if (checkWinner() != null) {
                guanyador.value = if (checkWinner() == true) "X" else "O"
            } else {
                // Canviar torn
                jugadorActual.value = if (jugadorActual.value) false else true
            }
        }
    }

    fun win(): Boolean{
        if (checkWinner() != null) return true
        else return false
    }

    fun checkWinner(): Boolean? {
        for (i in 0..2) {
            // Comprova files
            if (tauler.value[i][0] == tauler.value[i][1] && tauler.value[i][1] == tauler.value[i][2] && tauler.value[i][0] != null) {
                return if (tauler.value[i][0] == true) true else false
            }
            // Comprova columnes
            if (tauler.value[0][i] == tauler.value[1][i] && tauler.value[1][i] == tauler.value[2][i] && tauler.value[0][i] != null) {
                return if (tauler.value[0][i] == true) true else false
            }
        }
        // Comprova diagonals
        if (tauler.value[0][0] == tauler.value[1][1] && tauler.value[1][1] == tauler.value[2][2] && tauler.value[0][0] != null) {
            return if (tauler.value[0][0] == true) true else false
        }
        if (tauler.value[0][2] == tauler.value[1][1] && tauler.value[1][1] == tauler.value[2][0] && tauler.value[0][2] != null) {
            return if (tauler.value[0][2] == true) true else false
        }
        return null
    }
}

object Location {
    @Serializable
    data object MenuScreen
    @Serializable
    data object GameScreen
    @Serializable
    data class EndScreen (val winner: String)
}

@Composable
fun MenuScreen(navigateToGameScreen: ()-> Unit){
    Column(modifier = Modifier.fillMaxSize()
        .background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text("Let's play OXO!", fontSize = 3.em,
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(30.dp))
        Button(onClick = navigateToGameScreen){
            Text("Play")
        }
    }
}

@Composable
fun GameScreen(navigateToEndScreen: ()-> Unit){
    val gameVM = viewModel {GameViewModel()}
    if (gameVM.win()){
        navigateToEndScreen()
    }
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Column() {
            repeat(3) {
                Row() {
                    repeat(3) { col ->
                        Button(onClick = {
                            gameVM.doPlay(it, col)}) {
                            Text(toUserLetter(gameVM.tauler.value[it][col]))
                        }
                        Spacer(Modifier.width(3.dp))
                    }
                }
                Spacer(Modifier.height(3.dp))
            }
        }
    }
}

fun toUserLetter(b: Boolean?) = when(b){
    true -> "X"
    false -> "O"
    null -> ""
}

@Composable
fun EndScreen(navigateToMenuScreen: ()-> Unit, winner: String){
    val GameVM = viewModel {GameViewModel()}

    Column(modifier = Modifier.fillMaxSize()
        .background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text("The winner is...", fontSize = 2.em,
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(15.dp))
        Text(GameVM.guanyador.value.toString(), fontSize = 3.em,
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(30.dp))
        Button(onClick = navigateToMenuScreen){
            Text("Return to menu")
        }
    }
}

@Composable
fun OXONav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Location.MenuScreen) {
        composable<Location.MenuScreen> {
            MenuScreen(
                navigateToGameScreen = { navController.navigate(Location.GameScreen) },
            )
        }
        composable<Location.GameScreen> {
            GameScreen (
                navigateToEndScreen = { navController.navigate(Location.EndScreen) }
            )
        }
        composable<Location.EndScreen> { backStack ->
            val winner = backStack.toRoute<Destination.Screen3>().message
            EndScreen(navigateToMenuScreen = { navController.navigate(Location.MenuScreen) }, winner)
        }
    }
}