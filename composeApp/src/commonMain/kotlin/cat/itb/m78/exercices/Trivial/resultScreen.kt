package cat.itb.m78.exercices.Trivial

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.viewmodel.compose.viewModel
import m78exercices.composeapp.generated.resources.Audiowide_Regular
import m78exercices.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun resultScreen(points: Int, goToMenuScreen:()-> Unit){

    Column(modifier = Modifier.fillMaxSize().background(Color.Black), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Text("Your score: "+ points.toString(), fontSize = 5.em, color = Color.White, fontFamily = FontFamily(
            Font(Res.font.Audiowide_Regular)))
        Spacer(Modifier.height(30.dp))
        Button( onClick = {goToMenuScreen()},
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ){
            Text("Return to menu", color = Color.Black, fontFamily = FontFamily(Font(Res.font.Audiowide_Regular)))
        }
    }
}