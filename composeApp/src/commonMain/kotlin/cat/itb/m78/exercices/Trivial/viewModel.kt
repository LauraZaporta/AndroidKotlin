package cat.itb.m78.exercices.Trivial

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class TrivialVM : ViewModel(){
    val unusedQuestions = (0 until Questions.size).toMutableList()
    val randomQuestion = mutableStateOf(unusedQuestions.random())
    val question = mutableStateOf(Questions[randomQuestion.value])
    val answerResult = mutableStateOf("")
    val points = mutableStateOf(0)
    val rounds = mutableStateOf(10)
    val doneRounds = mutableStateOf(0)
    var answerPositions = mutableListOf(0,1,2,3)

    fun deleteUsedQuestion(randomQuestion : Int){
        unusedQuestions.remove(randomQuestion)
    }

    fun selectRandomQuestion(){
        if (unusedQuestions.size > 0){
            randomQuestion.value = (unusedQuestions.random())
            question.value = Questions[randomQuestion.value]
            generateRandomAnswer()
        }
    }
    fun generateRandomAnswer(){
        answerPositions = answerPositions.shuffled().toMutableList()
    }
    fun checkAnswer(answer : String, goToEndScreen : () -> Unit){
        doneRounds.value++
        if (doneRounds.value >= rounds.value){
            goToEndScreen()
        } else {
            if (question.value.answers[0] == answer){
                points.value++
                answerResult.value = "Correcte!"
            } else { answerResult.value = "Incorrecte! La resposta era " + question.value.answers[0]}
            deleteUsedQuestion(randomQuestion.value)
            selectRandomQuestion()
            if (unusedQuestions.size <= 0){
                goToEndScreen()
            }
        }
    }
}