package cat.itb.m78.exercices.Trivial

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class TrivialVM : ViewModel(){
    val settings = TrivialSettingsManager.get()
    val boxIsExpanded = mutableStateOf(false)
    val gender = mutableStateOf("")

    val unusedQuestions = (0 until chooseQuestionList().size).toMutableList()
    val randomQuestion = mutableStateOf(unusedQuestions.random())
    val question = mutableStateOf(chooseQuestionList()[randomQuestion.value])
    val answerResult = mutableStateOf("")
    var answerPositions = mutableListOf(0,1,2,3)
    val points = mutableStateOf(0)
    val rounds = mutableStateOf(settings.rounds)
    val doneRounds = mutableStateOf(0)
    val chosenQuestionList = mutableStateOf(settings.difficulty)

    fun deleteUsedQuestion(randomQuestion : Int){
        unusedQuestions.remove(randomQuestion)
    }

    fun chooseQuestionList() : List<Question>{
        if (chosenQuestionList.value == -1){
            return QuestionsEasy
        } else if (chosenQuestionList.value == 0){
            return QuestionsMedium
        } else { return QuestionsHard }
    }

    fun selectRandomQuestion(){
        if (unusedQuestions.size > 0){
            randomQuestion.value = (unusedQuestions.random())
            question.value = chooseQuestionList()[randomQuestion.value]
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