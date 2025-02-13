@file:OptIn(ExperimentalMaterial3Api::class)

package cat.itb.m78.exercices.Trivial

import androidx.compose.foundation.background
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import m78exercices.composeapp.generated.resources.Audiowide_Regular
import m78exercices.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

// 2 funcions

@Composable
fun settingsScreen(goToMenuScreen:() -> Unit){
    val settingsVM = viewModel { SettingsTrivialVM() }
    val currentSettings = TrivialSettingsManager.get()
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.padding(start = 200.dp).fillMaxWidth()){
            Text("Difficulty", fontFamily = FontFamily(Font(Res.font.Audiowide_Regular)))
            Spacer(Modifier.width(20.dp))

            ExposedDropdownMenuBox(
                expanded = settingsVM.isExpanded.value,
                onExpandedChange = { newValue ->
                    settingsVM.isExpanded.value = newValue
                }
            ) {
                TextField(
                    value = "",
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = settingsVM.isExpanded.value)
                    },
                    placeholder = {
                        Text(text = settingsVM.difficulty.value)
                    },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = settingsVM.isExpanded.value,
                    onDismissRequest = {
                        settingsVM.isExpanded.value = false
                    }
                ) {
                    DropdownMenuItem(
                        text = {
                            Text(text = "Easy")
                        },
                        onClick = {
                            TrivialSettingsManager.update(
                                currentSettings.copy(difficulty = -1)
                            )
                            settingsVM.isExpanded.value = false
                            settingsVM.difficulty.value = "Easy"
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Medium")
                        },
                        onClick = {
                            TrivialSettingsManager.update(
                                currentSettings.copy(difficulty = 0)
                            )
                            settingsVM.isExpanded.value = false
                            settingsVM.difficulty.value = "Medium"
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Hard")
                        },
                        onClick = {
                            TrivialSettingsManager.update(
                                currentSettings.copy(difficulty = 1)
                            )
                            settingsVM.isExpanded.value = false
                            settingsVM.difficulty.value = "Hard"
                        }
                    )
                }
            }
        }
        Spacer(Modifier.height(20.dp))
        Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.padding(start = 200.dp).fillMaxWidth()) {
            Text("Rounds", fontFamily = FontFamily(Font(Res.font.Audiowide_Regular)))
            Spacer(Modifier.width(20.dp))

            Column {
                Row(){
                    RadioButton(
                        selected = settingsVM.roundOption.value == 5,
                        onClick = {
                            settingsVM.updateRoundOption(5)
                        }
                    )
                    Spacer(Modifier.width(10.dp))
                    Text("5", Modifier.padding(top = 10.dp))
                }
                Row(){
                    RadioButton(
                        selected = settingsVM.roundOption.value == 10,
                        onClick = {
                            settingsVM.updateRoundOption(10)
                        }
                    )
                    Spacer(Modifier.width(10.dp))
                    Text("10", Modifier.padding(top = 10.dp))
                }
                Row(){
                    RadioButton(
                        selected = settingsVM.roundOption.value == 15,
                        onClick = {
                            settingsVM.updateRoundOption(15)
                        }
                    )
                    Spacer(Modifier.width(10.dp))
                    Text("15", Modifier.padding(top = 10.dp))
                }
            }
        }
        Spacer(Modifier.height(30.dp))
        Button( onClick = goToMenuScreen,
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text(
                "Return to menu",
                color = Color.White,
                fontFamily = FontFamily(Font(Res.font.Audiowide_Regular))
            )
        }
    }
}