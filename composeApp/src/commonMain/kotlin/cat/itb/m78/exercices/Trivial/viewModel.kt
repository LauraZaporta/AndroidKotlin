package cat.itb.m78.exercices.Trivial

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class TrivialVM : ViewModel(){
    val randomQuestion = mutableStateOf(Random.nextInt(Questions.size))
    val question = mutableStateOf(Questions[randomQuestion.value])
    val answerPositions = mutableListOf(0,1,2,3)

    fun selectRandomQuestion(){
        randomQuestion.value = (Random.nextInt(Questions.size));
        question.value = Questions[randomQuestion.value]
    }
    fun generateRandomAnswer() : List<Int>{
        return answerPositions.shuffled()
    }
}