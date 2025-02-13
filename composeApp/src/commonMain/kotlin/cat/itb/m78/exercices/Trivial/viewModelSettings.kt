package cat.itb.m78.exercices.Trivial

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SettingsTrivialVM : ViewModel(){
    var isExpanded = mutableStateOf(false)
    var difficulty = mutableStateOf("Select the difficulty")
    var roundOption = mutableStateOf(TrivialSettingsManager.get().rounds)

    fun updateRoundOption(num : Int){
        TrivialSettingsManager.update(
            TrivialSettingsManager.get().copy(rounds = num)
        )
        roundOption.value = TrivialSettingsManager.get().rounds
    }
}