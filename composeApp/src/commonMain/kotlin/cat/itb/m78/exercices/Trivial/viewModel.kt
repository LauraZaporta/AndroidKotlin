package cat.itb.m78.exercices.Trivial

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class TrivialVM : ViewModel(){
    val currentQuestions = chooseQuestionList()
    val boxIsExpanded = mutableStateOf(false)
    val gender = mutableStateOf("")

    val unusedQuestions = (0 until currentQuestions.size).toMutableList()
    val randomQuestion = mutableStateOf(unusedQuestions.random())
    val question = mutableStateOf(chooseQuestionList()[randomQuestion.value])
    val answerResult = mutableStateOf("")
    var answerPositions = mutableListOf(0,1,2,3)
    val points = mutableStateOf(0)
    val rounds = mutableStateOf(TrivialSettingsManager.get().rounds)
    val doneRounds = mutableStateOf(0)

    fun deleteUsedQuestion(randomQuestion : Int){
        unusedQuestions.remove(randomQuestion)
    }

    fun chooseQuestionList(): List<Question> {
        return when (TrivialSettingsManager.get().difficulty) {
            -1 -> QuestionsEasy
            0 -> QuestionsMedium
            else -> QuestionsHard
        }
    }

    fun selectRandomQuestion(){
        if (unusedQuestions.size > 0){
            randomQuestion.value = (unusedQuestions.random())
            question.value = currentQuestions[randomQuestion.value]
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