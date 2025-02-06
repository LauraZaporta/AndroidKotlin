package cat.itb.m78.exercices

import androidx.compose.runtime.*
import cat.itb.m78.exercices.Trivial.TrivialNavigation
import cat.itb.m78.exercices.stateless.Resource
import cat.itb.m78.exercices.theme.AppTheme

@Composable
internal fun App() = AppTheme {
    TrivialNavigation()
}
