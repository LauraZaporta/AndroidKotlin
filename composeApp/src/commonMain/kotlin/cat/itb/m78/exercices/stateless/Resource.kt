package cat.itb.m78.exercices.stateless

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.generatedFace
import m78exercices.composeapp.generated.resources.resourceString
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun Resource(){
    Column (horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(300.dp)){
        Text(stringResource(Res.string.resourceString))
        Image(painter = painterResource(Res.drawable.generatedFace),
            contentDescription = null,
            modifier = Modifier.size(150.dp))
    }
}