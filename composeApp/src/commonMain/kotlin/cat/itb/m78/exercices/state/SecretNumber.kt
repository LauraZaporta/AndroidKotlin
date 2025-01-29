package cat.itb.m78.exercices.state

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

const val MsgInfo = "Endevina el número secret"
const val SecretNumber = 15

@Composable
fun SecretNumber(){
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()){
        val input = remember { mutableStateOf("") }
        val displayInfo = remember { mutableStateOf("")}
        Text(MsgInfo)
        TextField(input.value, onValueChange = {input.value = it})
        Button(onClick = {
            val value = input.value.toIntOrNull()

            if (value == null){
                displayInfo.value = "Introdueix un número"
            } else if (value == SecretNumber){
                displayInfo.value = "L'has encertat"
            } else if (input.value.toInt() < SecretNumber){
                displayInfo.value = "El número és més gran"
            } else {
                displayInfo.value = "El número és més petit"
            }
        }){
            Text("Submit")
        }
        Text(displayInfo.value)
    }
}