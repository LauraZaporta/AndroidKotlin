package cat.itb.m78.exercices.Trivial

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SettingsTrivialVM : ViewModel(){
    val isExpanded = mutableStateOf(false)
    val difficulty = mutableStateOf("Select the difficulty")
    val roundOption = mutableStateOf(TrivialSettingsManager.get().rounds)
    val secondsPerRound = mutableStateOf(TrivialSettingsManager.get().time)

    fun updateRoundOption(num : Int){
        TrivialSettingsManager.update(
            TrivialSettingsManager.get().copy(rounds = num)
        )
        roundOption.value = TrivialSettingsManager.get().rounds
    }
}