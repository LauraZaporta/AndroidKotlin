package cat.itb.m78.exercices

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cat.itb.m78.exercices.stateless.Contact
import cat.itb.m78.exercices.stateless.MessagesList
import cat.itb.m78.exercices.stateless.Resource
import cat.itb.m78.exercices.theme.AppTheme
import org.jetbrains.compose.reload.DevelopmentEntryPoint

@Composable
internal fun App() = AppTheme {
    MessagesList()
}
