package cat.itb.m78.exercices.Trivial

import androidx.compose.foundation.background
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
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import m78exercices.composeapp.generated.resources.Audiowide_Regular
import m78exercices.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun gameScreen(goToResultScreen: (Int) -> Unit){
    val trivialVM = viewModel { TrivialVM() }

    gameScreenArguments({ goToResultScreen(trivialVM.points.value) }, trivialVM.question.value.answers[trivialVM.answerPositions[0]],
        trivialVM.question.value.answers[trivialVM.answerPositions[1]], trivialVM.question.value.answers[trivialVM.answerPositions[2]],
        trivialVM.question.value.answers[trivialVM.answerPositions[3]], trivialVM.answerResult.value, trivialVM.points.value,
        trivialVM.timer, trivialVM.chosenSeconds)
}

@Composable
fun gameScreenArguments(goToResultScreen:(Int) -> Unit, ansOne : String,
                        ansTwo : String, ansThree : String, ansFour : String,
                        ansResult : String, points : Int, timer: MutableState<Float>,
                        chosenSeconds : Float
){
    val trivialVM = viewModel { TrivialVM() }

    LaunchedEffect(timer.value) { //X Dintre de ViewModel per complicacions
        if (timer.value > 0) {
            delay(10L)
            val updatedTime = (timer.value - 0.01).coerceAtLeast(0.0)
            timer.value = updatedTime.toFloat()
        } else {
            trivialVM.changeQuestionTimeOut{goToResultScreen(points)}
        }
    }

    @Composable
    fun generateButton(ans : String){
        Button( onClick = {
            trivialVM.checkAnswer(ans) {
                goToResultScreen(points)
            }
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
                generateButton(ansOne)
                Spacer(Modifier.width(10.dp))
                generateButton(ansTwo)
            }
            Spacer(Modifier.height(10.dp))
            Row() {
                generateButton(ansThree)
                Spacer(Modifier.width(10.dp))
                generateButton(ansFour)
            }
        }
        Spacer(Modifier.height(30.dp))
        LinearProgressIndicator(
            progress = {
                (timer.value / chosenSeconds)
            },
            modifier = Modifier
                .height(10.dp)
                .width(200.dp)
        )
        Spacer(Modifier.height(30.dp))
        if (ansResult == "Correcte!"){
            Text(ansResult, color = Color.Green, fontFamily = FontFamily(Font(Res.font.Audiowide_Regular)))
        } else {Text(ansResult, color = Color.Red, fontFamily = FontFamily(Font(Res.font.Audiowide_Regular)))}
    }
}