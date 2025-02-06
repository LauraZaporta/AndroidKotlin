package cat.itb.m78.exercices.Trivial

class Question(
    val question: String,
    val answers: List<String>,
    val indexRightAnswe: Int = 0 //La resposta correcta sempre serà la primera
)

val Questions: List<Question> = listOf(
    Question("Quan va ser la hambruna russa de Tàrtara?",
        listOf("1921-1922","1954-1955","1919","1905-1906")
    )
)