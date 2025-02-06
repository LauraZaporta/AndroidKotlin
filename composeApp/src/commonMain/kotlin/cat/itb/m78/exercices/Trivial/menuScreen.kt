package cat.itb.m78.exercices.Trivial

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.Navigation.GameViewModel
import m78exercices.composeapp.generated.resources.Audiowide_Regular
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.trivial
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

@Composable
fun menuScreen(goToGameScreen: ()-> Unit,
               goToSettingsScreen: () -> Unit){
    val trivialVM = viewModel { TrivialVM() }
    val brushBackground = Brush.linearGradient(listOf(Color(0XFF3CFAA3), Color(0XFFACFA3E)))

    Column(modifier = Modifier.fillMaxSize() .background(brushBackground),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){

        Text("Trivial", fontSize = 5.em, color = Color.Black, fontFamily = FontFamily(Font(Res.font.Audiowide_Regular)))
        Spacer(Modifier.height(20.dp))
        Image(painter = painterResource(Res.drawable.trivial),
            contentDescription = null,
            modifier = Modifier.size(200.dp))
        Spacer(Modifier.height(40.dp))
        Button( onClick = {goToGameScreen()
                          trivialVM.generateRandomAnswer()
                          trivialVM.selectRandomQuestion()},
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ){
            Text("New game", color = Color.White, fontFamily = FontFamily(Font(Res.font.Audiowide_Regular)))
        }
        Spacer(Modifier.height(10.dp))
        Button( onClick = goToSettingsScreen,
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ){
            Text("Settings", color = Color.White, fontFamily = FontFamily(Font(Res.font.Audiowide_Regular)))
        }
    }
}