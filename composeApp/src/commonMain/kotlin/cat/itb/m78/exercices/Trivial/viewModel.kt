package cat.itb.m78.exercices.Trivial

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlin.random.Random

class TrivialVM : ViewModel(){
    private val currentQuestions = chooseQuestionList()
    private val unusedQuestions = (0 until currentQuestions.size).toMutableList()
    private val randomQuestion = mutableStateOf(unusedQuestions.random())
    val question = mutableStateOf(chooseQuestionList()[randomQuestion.value])
    val answerResult = mutableStateOf("")
    var answerPositions = mutableListOf(0,1,2,3)
    val points = mutableStateOf(0)
    val rounds = mutableStateOf(TrivialSettingsManager.get().rounds)
    val doneRounds = mutableStateOf(0)

    val chosenSeconds = TrivialSettingsManager.get().time
    val timer = mutableStateOf(chosenSeconds)

    private fun deleteUsedQuestion(randomQuestion : Int){
        unusedQuestions.remove(randomQuestion)
    }
    private fun chooseQuestionList(): List<Question> {
        return when (TrivialSettingsManager.get().difficulty) {
            -1 -> QuestionsEasy
            0 -> QuestionsMedium
            else -> QuestionsHard
        }
    }
    private fun selectRandomQuestion(){
        if (unusedQuestions.size > 0 && doneRounds.value < rounds.value){
            timer.value = chosenSeconds
            randomQuestion.value = (unusedQuestions.random())
            question.value = currentQuestions[randomQuestion.value]
            generateRandomAnswer()
        }
    }
    fun changeQuestionTimeOut(goToEndScreen : () -> Unit){
        doneRounds.value++
        if (doneRounds.value >= rounds.value){
            goToEndScreen()
        }
        selectRandomQuestion()
        answerResult.value = ""
    }
    private fun generateRandomAnswer(){
        answerPositions = answerPositions.shuffled().toMutableList()
    }
    fun checkAnswer(answer : String, goToEndScreen : () -> Unit){
        doneRounds.value++
        if (question.value.answers[0] == answer){
            points.value++
            answerResult.value = "Correcte!"
        } else { answerResult.value = "Incorrecte! La resposta era " + question.value.answers[0]}
        deleteUsedQuestion(randomQuestion.value)
        if (doneRounds.value >= rounds.value){
            goToEndScreen()
        }
        selectRandomQuestion()
    }
}