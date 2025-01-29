package cat.itb.m78.exercices.Navigation

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.runtime.remember
import androidx.compose.animation.core.rememberTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import io.ktor.http.HttpHeaders.Destination
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private sealed interface Screen { //Es declara cada pantalla i els paràmetres que necessiten
    data object MenuScreen : Screen
    data object Screen1 : Screen
    data object Screen2 : Screen
    data class Screen3(val message : String) : Screen
}

private class ManualNavAppViewModel : ViewModel() { //Es declaren els estats que poden variar per canviar la pantalla
    val currentScreen = mutableStateOf<Screen>(Screen.MenuScreen)
    fun navigateTo(screen: Screen) {
        currentScreen.value = screen
    }
}

// Es defineix cada pantalla com a funció
@Composable
fun MenuScreen(navigateToScreen1: ()-> Unit,
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
fun Screen1(navigateToMenuScreen: ()-> Unit){
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
fun Screen2(navigateToMenuScreen: ()-> Unit){
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
fun Screen3(navigateToMenuScreen: ()-> Unit, message: String){
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
fun ManualNav() {
    val viewModel = viewModel { ManualNavAppViewModel() }
    val currentScreen = viewModel.currentScreen.value
    when (currentScreen) { //S'invoca la funció de cada pantalla en cada cas
        Screen.Screen1 -> Screen1(
            navigateToMenuScreen = { viewModel.navigateTo(Screen.MenuScreen) }
        )
        is Screen.Screen2 -> Screen2(
            navigateToMenuScreen = { viewModel.navigateTo(Screen.MenuScreen) }
        )
        is Screen.Screen3 -> Screen3(
            navigateToMenuScreen = { viewModel.navigateTo(Screen.MenuScreen) },
            message = (currentScreen).message
        )
        is Screen.MenuScreen -> MenuScreen(
            navigateToScreen1 = { viewModel.navigateTo(Screen.Screen1) },
            navigateToScreen2 = { viewModel.navigateTo(Screen.Screen2) },
            navigateToScreen3H = { viewModel.navigateTo(Screen.Screen3("Hello")) },
            navigateToScreen3B = { viewModel.navigateTo(Screen.Screen3("Bye")) }
        )
    }
}