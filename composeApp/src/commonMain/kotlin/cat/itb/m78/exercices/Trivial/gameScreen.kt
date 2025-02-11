package cat.itb.m78.exercices.Trivial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import m78exercices.composeapp.generated.resources.Audiowide_Regular
import m78exercices.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun gameScreen(goToResultScreen:(Int) -> Unit){
    val trivialVM = viewModel { TrivialVM() }

    // Quan la pantalla es crea o quan es canvia la pregunta s'executa
    LaunchedEffect(Unit) {
        trivialVM.selectRandomQuestion()
    }
    val answerOne = trivialVM.question.value.answers[trivialVM.answerPositions[0]]
    val answerTwo = trivialVM.question.value.answers[trivialVM.answerPositions[1]]
    val answerThree = trivialVM.question.value.answers[trivialVM.answerPositions[2]]
    val answerFour = trivialVM.question.value.answers[trivialVM.answerPositions[3]]

    @Composable
    fun generateButton(ans : String){
        Button( onClick = {
            trivialVM.checkAnswer(ans) {
                goToResultScreen(trivialVM.points.value)
            }
            trivialVM.selectRandomQuestion()
        },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ){
            Text(ans, color = Color.White, fontFamily = FontFamily(Font(Res.font.Audiowide_Regular)))
        }
    }

    Column(modifier = Modifier.fillMaxWidth().padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,){
        Text("Round " + (trivialVM.doneRounds.value + 1) + "/" + trivialVM.rounds.value)
    }
    Spacer(Modifier.height(30.dp))
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){

        Text(trivialVM.question.value.question)
        Spacer(Modifier.height(20.dp))

        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row() {
                generateButton(answerOne)
                Spacer(Modifier.width(10.dp))
                generateButton(answerTwo)
            }
            Spacer(Modifier.height(10.dp))
            Row() {
                generateButton(answerThree)
                Spacer(Modifier.width(10.dp))
                generateButton(answerFour)
            }
        }
        Spacer(Modifier.height(20.dp))
        if (trivialVM.answerResult.value == "Correcte!"){
            Text(trivialVM.answerResult.value, color = Color.Green, fontFamily = FontFamily(Font(Res.font.Audiowide_Regular)))
        } else {Text(trivialVM.answerResult.value, color = Color.Red, fontFamily = FontFamily(Font(Res.font.Audiowide_Regular)))}
    }
}