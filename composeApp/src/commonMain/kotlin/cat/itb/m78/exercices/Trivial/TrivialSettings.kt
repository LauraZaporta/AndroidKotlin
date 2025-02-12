package cat.itb.m78.exercices.Trivial

data class TrivialSettings(
    val difficulty: Int = 0,
    val rounds: Int = 10
)

data object TrivialSettingsManager {
    private var settings = TrivialSettings()

    fun update(newSettings: TrivialSettings) {
        settings = newSettings
    }

    fun get() = settings
}