package cat.itb.m78.exercices

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cat.itb.m78.exercices.Navigation.LibNavScreenSample
import cat.itb.m78.exercices.Navigation.ManualNav
import cat.itb.m78.exercices.Navigation.Screen1
import cat.itb.m78.exercices.Navigation.Screen2
import cat.itb.m78.exercices.Navigation.Screen3
import cat.itb.m78.exercices.Navigation.TikTok
import cat.itb.m78.exercices.state.DiceRoller
import cat.itb.m78.exercices.state.Good
import cat.itb.m78.exercices.state.SayHello
import cat.itb.m78.exercices.state.SecretNumber
import cat.itb.m78.exercices.stateless.Contact
import cat.itb.m78.exercices.stateless.MessagesList
import cat.itb.m78.exercices.stateless.Resource
import cat.itb.m78.exercices.theme.AppTheme
import cat.itb.m78.exercices.viewModel.FunCounterView
import cat.itb.m78.exercices.viewModel.FunPurchaseListView
import cat.itb.m78.exercices.viewModel.PurchaseList
import org.jetbrains.compose.reload.DevelopmentEntryPoint

@Composable
internal fun App() = AppTheme {
    TikTok()
}
