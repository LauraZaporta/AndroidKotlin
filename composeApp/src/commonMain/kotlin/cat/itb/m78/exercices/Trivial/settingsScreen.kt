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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation.width
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import m78exercices.composeapp.generated.resources.Audiowide_Regular
import m78exercices.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun settingsScreen(goToMenuScreen:() -> Unit){
    val settingsVM = viewModel { SettingsTrivialVM() }

    settingsScreenArguments({goToMenuScreen()}, settingsVM.isExpanded,
        settingsVM.difficulty, settingsVM.roundOption.value, settingsVM::updateRoundOption,
        settingsVM.secondsPerRound)
}

@Composable
fun settingsScreenArguments(goToMenuScreen:() -> Unit, isExpanded: MutableState<Boolean>,
                            difficulty: MutableState<String>, roundOption : Int,
                            updateRoundOption: (Int)-> Unit, secondsPerRound : MutableState<Float>){
    val currentSettings = TrivialSettingsManager.get()

    @Composable
    fun generateDropDownMenuItem(text : String, num : Int){
        DropdownMenuItem(
            text = {
                Text(text = text)
            },
            onClick = {
                TrivialSettingsManager.update(
                    currentSettings.copy(difficulty = num)
                )
                isExpanded.value = false
                difficulty.value = text
            }
        )
    }
    @Composable
    fun generateRadioButton(num : Int){
        Row(){
            RadioButton(
                selected = roundOption == num,
                onClick = { updateRoundOption(num) }
            )
            Spacer(Modifier.width(10.dp))
            Text(num.toString(), Modifier.padding(top = 10.dp))
        }
    }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.padding(start = 200.dp).fillMaxWidth()){
            Text("Difficulty", fontFamily = FontFamily(Font(Res.font.Audiowide_Regular)))
            Spacer(Modifier.width(20.dp))

            ExposedDropdownMenuBox(
                expanded = isExpanded.value,
                onExpandedChange = { newValue -> isExpanded.value = newValue }
            ) {
                TextField(
                    value = "",
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded.value)
                    },
                    placeholder = {
                        Text(text = difficulty.value)
                    },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = isExpanded.value,
                    onDismissRequest = {
                        isExpanded.value = false
                    }
                ) {
                    generateDropDownMenuItem("Easy", -1)
                    generateDropDownMenuItem("Medium", 0)
                    generateDropDownMenuItem("Hard", 1)
                }
            }
        }
        Spacer(Modifier.height(20.dp))
        Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.padding(start = 200.dp).fillMaxWidth()) {
            Text("Rounds", fontFamily = FontFamily(Font(Res.font.Audiowide_Regular)))
            Spacer(Modifier.width(20.dp))

            Column {
                generateRadioButton(5)
                generateRadioButton(10)
                generateRadioButton(15)
            }
        }
        Spacer(Modifier.height(30.dp))
        Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.padding(start = 200.dp).fillMaxWidth()){
            Text("Time per round", fontFamily = FontFamily(Font(Res.font.Audiowide_Regular)))
            Spacer(Modifier.width(20.dp))
            Row(modifier = Modifier.width(200.dp)){
                Slider(
                    value = secondsPerRound.value,
                    onValueChange = { secondsPerRound.value = it
                            TrivialSettingsManager.update(
                            currentSettings.copy(time = secondsPerRound.value)
                            )},
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colorScheme.secondary,
                        activeTrackColor = MaterialTheme.colorScheme.secondary,
                        inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                    ),
                    steps = 70,
                    valueRange = 3f..10f
                )
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