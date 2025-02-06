package cat.itb.m78.exercices.Trivial

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun gameScreen(){
    val trivialVM = viewModel { TrivialVM() }
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){

        Text(trivialVM.question.value.question)
        Spacer(Modifier.height(20.dp))

        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){

            Row(){
                Button(onClick = {}){
                    Text(trivialVM.question.value.answers[trivialVM.answerPositions[0]])
                }
                Spacer(Modifier.width(10.dp))
                Button(onClick = {}){
                    Text(trivialVM.question.value.answers[trivialVM.answerPositions[1]])
                }
            }
            Spacer(Modifier.height(10.dp))
            Row(){
                Button(onClick = {}){
                    Text(trivialVM.question.value.answers[trivialVM.answerPositions[2]])
                }
                Spacer(Modifier.width(10.dp))
                Button(onClick = {}){
                    Text(trivialVM.question.value.answers[trivialVM.answerPositions[3]])
                }
            }
        }
    }
}