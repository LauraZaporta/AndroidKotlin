package cat.itb.m78.exercices.stateless

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessagesList(){
    data class Message(val author: String, val body: String)

    val names = listOf(
        "Ellison Curry",
        "Briggs Willis",
        "Alexa Murphy",
        "Cameron Berry",
        "Annabelle Villarreal",
        "Nikolai Wiley",
        "Lauryn Morrow",
        "Kyree Hardy",
        "Jessica Lang",
        "Wells Wilson",
        "Luna Foster",
        "Kayden Taylor",
        "Sofia Mann",
        "Nehemiah Randall",
        "Christina Gordon",
        "Karter Kramer",
        "Hanna Morales",
        "Aaron Velez",
        "Megan Delarosa",
        "Osiris Johnson",
        "Emma Atkins",
        "Cason McKee",
        "Kori Walls",
        "Larry Shepherd",
    )
    val body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vestibulum nunc."
    val messages = List(100){
        Message(names.random(), body)
    }

    Scaffold(
        bottomBar = {
            TopAppBar(
                title = { Text("App Bar Title") },
                navigationIcon = {Icon(Icons.AutoMirrored.Filled.ArrowBack, "back")}) },
        floatingActionButton = {
            FloatingActionButton(onClick = {}){
                Icon(Icons.Default.Add, "add")
            }
        }
    ) {
        LazyColumn(modifier = Modifier.padding(15.dp), verticalArrangement = Arrangement.spacedBy(15.dp)){
            items(names){
                Card(modifier = Modifier.fillMaxSize()){
                    Column(modifier = Modifier.padding(15.dp), verticalArrangement = Arrangement.spacedBy(15.dp)){
                        Text(it)
                        Text(body)
                    }
                }
            }
        }
    }
}