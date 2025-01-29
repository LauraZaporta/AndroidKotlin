package cat.itb.m78.exercices.Navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

object Destination {
    @Serializable
    data object MenuScreen
    @Serializable
    data object Screen1
    @Serializable
    data object Screen2
    @Serializable
    data class Screen3(val message: String)
}

@Composable
fun MenuScreenL(navigateToScreen1: ()-> Unit,
               navigateToScreen2: () -> Unit,
               navigateToScreen3H: () -> Unit,
               navigateToScreen3B: () -> Unit){
    Column(){
        Button(onClick = navigateToScreen1){
            Text("Screen 1")
        }
        Spacer(Modifier.height(5.dp))
        Button(onClick = navigateToScreen2){
            Text("Screen 2")
        }
        Spacer(Modifier.height(5.dp))
        Button(onClick = navigateToScreen3H){
            Text("Screen 3 - Hello")
        }
        Spacer(Modifier.height(5.dp))
        Button(onClick = navigateToScreen3B){
            Text("Screen 3 -  Bye")
        }
    }
}

@Composable
fun Screen1L(navigateToMenuScreen: ()-> Unit){
    Box(modifier = Modifier.fillMaxSize()
        .background(Color.Green)){
        Column(modifier = Modifier.align(Alignment.BottomEnd)){
            Text("Screen 1")
            Spacer(Modifier.height(2.dp))
            Button(onClick = navigateToMenuScreen){
                Text("Main Menu")
            }
        }
    }
}
@Composable
fun Screen2L(navigateToMenuScreen: ()-> Unit){
    Column(modifier = Modifier.fillMaxSize()
        .background(Color.Red),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Text("Screen 2")
        Spacer(Modifier.height(2.dp))
        Button(onClick = navigateToMenuScreen){
            Text("Main Menu")
        }
    }
}
@Composable
fun Screen3L(navigateToMenuScreen: ()-> Unit, message: String){
    Column(modifier = Modifier.fillMaxSize()
        .background(Color.Blue),
        horizontalAlignment = Alignment.CenterHorizontally){
        Text("Screen 3")
        Spacer(Modifier.height(2.dp))
        Text(message) //Pot ser Hello o Bye
        Spacer(Modifier.height(2.dp))
        Button(onClick = navigateToMenuScreen){
            Text("Main Menu")
        }
    }
}

@Composable
fun LibNavScreenSample() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destination.MenuScreen) {
        composable<Destination.Screen1> {
            Screen1(
                navigateToMenuScreen = { navController.navigate(Destination.MenuScreen) },
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