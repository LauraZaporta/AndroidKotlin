package cat.itb.m78.exercices.Navigation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    // Estat del tauler. Inicialment, totes les caselles són null (vacío)
    var tauler = mutableStateOf(List(3) { MutableList(3) { null as Boolean? } })
        private set

    // Estat del jugador actual. Comença amb X
    var jugadorActual = mutableStateOf("X")
        private set

    // Estat del guanyador (null si encara no hi ha guanyador)
    var guanyador = mutableStateOf<String?>(null)
        private set

    // Funció per fer un moviment en el tauler
    fun ferMoviment(fila: Int, columna: Int) {
        // Comprovar que la casella estigui buida i que encara no hi hagi guanyador
        if (tauler.value[fila][columna] == null && guanyador.value == null) {
            // Actualitzar el tauler
            tauler.value[fila][columna] = if (jugadorActual.value == "X") true else false

            // Comprovar si hi ha un guanyador
            if (comprovarGuanyador()) {
                guanyador.value = jugadorActual.value
            } else {
                // Canviar el torn del jugador
                jugadorActual.value = if (jugadorActual.value == "X") "O" else "X"
            }
        }
    }

    // Funció per comprovar si algun jugador ha guanyat
    private fun comprovarGuanyador(): Boolean {
        // Comprovar files, columnes i diagonals
        for (i in 0..2) {
            // Comprovar files
            if (tauler.value[i][0] == tauler.value[i][1] && tauler.value[i][1] == tauler.value[i][2] && tauler.value[i][0] != null) {
                return true
            }
            // Comprovar columnes
            if (tauler.value[0][i] == tauler.value[1][i] && tauler.value[1][i] == tauler.value[2][i] && tauler.value[0][i] != null) {
                return true
            }
        }
        // Comprovar diagonals
        if (tauler.value[0][0] == tauler.value[1][1] && tauler.value[1][1] == tauler.value[2][2] && tauler.value[0][0] != null) {
            return true
        }
        if (tauler.value[0][2] == tauler.value[1][1] && tauler.value[1][1] == tauler.value[2][0] && tauler.value[0][2] != null) {
            return true
        }
        return false
    }