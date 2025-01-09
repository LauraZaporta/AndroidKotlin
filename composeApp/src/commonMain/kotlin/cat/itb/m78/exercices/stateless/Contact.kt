package cat.itb.m78.exercices.stateless

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.generatedFace
import m78exercices.composeapp.generated.resources.resourceString
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun Contact(){
    data class Contact(val fullName: String, val email: String, val phone: String)
    val contact = Contact("Marta Casserres", "marta@example.com", "934578484")

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(Res.drawable.generatedFace),
            contentDescription = null,
            modifier = Modifier.clip(CircleShape)
            .size(150.dp))
        Spacer(Modifier.height(5.dp))
        Text(contact.fullName, fontSize = 2.em, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(10.dp))
        Card(modifier = Modifier.padding(10.dp)){
            Column(modifier = Modifier.padding(15.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
                Row(){
                    Icon(Icons.Default.Email, null)
                    Spacer(Modifier.width(5.dp))
                    Text(contact.email)
                }
                Row(){
                    Icon(Icons.Default.Call, null)
                    Spacer(Modifier.width(5.dp))
                    Text(contact.phone)
                }
            }
        }
    }
}