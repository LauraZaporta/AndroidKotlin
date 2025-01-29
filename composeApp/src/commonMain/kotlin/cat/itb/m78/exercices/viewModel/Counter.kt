package cat.itb.m78.exercices.viewModel

import androidx.compose.animation.core.rememberTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.viewmodel.compose.viewModel
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.background
import m78exercices.composeapp.generated.resources.tapestry
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

class Counter : ViewModel() {
    var onePoints = mutableStateOf(0)
    var twoPoints = mutableStateOf(0)
    fun addPointOne(){
        onePoints.value++
    }
    fun addPointTwo(){
        twoPoints.value++
    }
}

@Composable
fun FunCounterView(){
    val teamOneText = "Team A"
    val teamTwoText = "Team B"
    val addPointText = "Add point"
    val background = Res.drawable.background

    val viewModel = viewModel { Counter() }

    FunCounter(teamOneText, teamTwoText, addPointText, background, viewModel.onePoints.value,
        viewModel.twoPoints.value, viewModel::addPointOne, viewModel::addPointTwo)
}

@Composable
fun FunCounter(teamOneText : String, teamTwoText : String,
                   addPointText : String, background : DrawableResource,
                   onePoints : Int, twoPoints : Int,
                   addPointOne : ()->Unit, addPointTwo : ()->Unit){
    Box(modifier = Modifier.fillMaxSize()){
        //BACKGROUND
        Image(painter = painterResource(background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize())

        //TWO COUNTERS
        Row(modifier = Modifier.align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically){
            Column(horizontalAlignment = Alignment.CenterHorizontally){
                Button(onClick = {addPointOne()}){
                    Text(addPointText)
                }
                Spacer(Modifier.height(10.dp))
                Text(teamOneText,
                    textDecoration = TextDecoration.Underline,
                    color = Color.White,
                    fontSize = 2.em,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(5.dp))
                Text(onePoints.toString(),
                    color = Color.Blue,
                    fontSize = 3.em,
                    fontWeight = FontWeight.ExtraBold)
            }
            Spacer(Modifier.width(40.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally){
                Button(onClick = {addPointTwo()}){
                    Text(addPointText)
                }
                Spacer(Modifier.height(10.dp))
                Text(teamTwoText,
                    textDecoration = TextDecoration.Underline,
                    color = Color.White,
                    fontSize = 2.em,
                    fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(5.dp))
                Text(twoPoints.toString(),
                    color = Color.Red,
                    fontSize = 3.em,
                    fontWeight = FontWeight.ExtraBold)
            }
        }

    }
}
