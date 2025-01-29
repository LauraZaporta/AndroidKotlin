package cat.itb.m78.exercices.state

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.runtime.collection.mutableVectorOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.dice_1
import m78exercices.composeapp.generated.resources.dice_2
import m78exercices.composeapp.generated.resources.dice_3
import m78exercices.composeapp.generated.resources.dice_4
import m78exercices.composeapp.generated.resources.dice_5
import m78exercices.composeapp.generated.resources.dice_6
import m78exercices.composeapp.generated.resources.generatedFace
import m78exercices.composeapp.generated.resources.tapestry
import m78exercices.composeapp.generated.resources.title
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import kotlin.random.Random

fun chooseDice() : Int{
    return Random.nextInt(1, 7)
}

fun getDice(num:Int) : DrawableResource{
    return when (num){
        1 -> Res.drawable.dice_1
        2 -> Res.drawable.dice_2
        3 -> Res.drawable.dice_3
        4 -> Res.drawable.dice_4
        5 -> Res.drawable.dice_5
        6 -> Res.drawable.dice_6
        else ->{
            Res.drawable.dice_1
        }
    }
}

@Composable
fun DiceRoller(){
    var numDiceOne by remember { mutableStateOf(0) }
    var numDiceTwo by remember { mutableStateOf(0) }
    var counter by remember { mutableStateOf(0) }
    val text = remember { mutableStateOf("")}

    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(Res.drawable.tapestry),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize())

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()){

            Spacer(Modifier.weight(1f))

            Image(painter = painterResource(Res.drawable.title),
                contentDescription = null)

            Spacer(Modifier.height(15.dp))

            Button(modifier = Modifier.fillMaxWidth(),
                   onClick = {
                    numDiceOne = chooseDice()
                    numDiceTwo = chooseDice()
                    counter++
                    }){
                Text("Roll the dice")
            }

            Spacer(Modifier.height(15.dp))

            Row(){
                if (counter > 0){
                    Image(painter = painterResource(getDice(numDiceOne)),
                        contentDescription = null)
                    Image(painter = painterResource(getDice(numDiceTwo)),
                        contentDescription = null)
                }
            }
            Spacer(Modifier.height(15.dp))

            if (numDiceOne == 6 && numDiceTwo == 6){
                text.value = "JACKPOT!"
            } else { text.value = ""}

            Text(text.value, fontSize = 2.em, fontWeight = FontWeight.Bold, color = Color.White)

            Spacer(Modifier.weight(1f))
        }
    }
}