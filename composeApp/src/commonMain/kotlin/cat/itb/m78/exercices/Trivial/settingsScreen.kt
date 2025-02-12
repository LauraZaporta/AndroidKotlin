@file:OptIn(ExperimentalMaterial3Api::class)

package cat.itb.m78.exercices.Trivial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun settingsScreen(){
    val trivialVM = viewModel { TrivialVM() }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        Row(horizontalArrangement = Arrangement.Center){
            Text("Difficulty")
            Spacer(Modifier.width(10.dp))

            ExposedDropdownMenuBox(
                expanded = trivialVM.boxIsExpanded.value,
                onExpandedChange = { trivialVM.boxIsExpanded.value = it}){
                TextField(
                    value = trivialVM.gender.value,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = trivialVM.boxIsExpanded.value)
                    },
                    modifier = Modifier.menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = trivialVM.boxIsExpanded.value,
                    onDismissRequest = {trivialVM.boxIsExpanded.value = false}
                ){
                    DropdownMenuItem(
                        text = {Text("Easy")},
                        onClick = {})
                    DropdownMenuItem(
                        text = {Text("Medium")},
                        onClick = {})
                    DropdownMenuItem(
                        text = {Text("Hard")},
                        onClick = {})
                }
            }
        }
    }
}