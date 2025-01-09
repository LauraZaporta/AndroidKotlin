package cat.itb.m78.exercices.stateless

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

@Composable
fun Welcome(){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Text("Welcome!", modifier = Modifier.padding(bottom = 10.dp), fontSize = 1.75.em) //Manera 1 space
        Text("Start learning now")
        Spacer(Modifier.height(50.dp)) //Manera 2 space
        Button(onClick = {}){
            Text("Login")
        }
        Button(onClick = {}){
            Text("Register")
        }
    }
}