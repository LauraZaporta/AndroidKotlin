package cat.itb.m78.exercices.state

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SayHello(){
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()){
        var showDialog by remember { mutableStateOf(false) }
        val input = remember { mutableStateOf("")}
        TextField(input.value,
            label = {Text("Name")},
            onValueChange = { input.value = it })
        Button(onClick = {showDialog = true}){
            Text("SayHello")
        }
        if(showDialog)
            AlertDialog(
                onDismissRequest={showDialog = false},
                confirmButton={},
                text = {Text("Hello " + input.value)}
            )
    }
}