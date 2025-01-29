package cat.itb.m78.exercices.state

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material3.Text

@Composable
fun Good(){
    Column(){
        val text = remember { mutableStateOf("Good?") }
        Text(text.value)
        Button(onClick = {
            text.value = "Good morning!"
        }) { Text("Morning") }
        Button(onClick = {
            text.value = "Good night!"
        }) { Text("Night") }
    }
}