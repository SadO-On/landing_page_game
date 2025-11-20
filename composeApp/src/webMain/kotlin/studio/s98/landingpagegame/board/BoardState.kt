package studio.s98.landingpagegame.board

import studio.s98.landingpagegame.util.generateRandomUUIDString
import studio.s98.landingpagegame.models.Letter

data class BoardState(
    val grid: List<List<Letter>> = ArrayList<ArrayList<Letter>>(),
    val percent: Float = 100f,
    val time: String = "0:00",
    var points: Int = 200,
    var isNavigate: Boolean = false,
    var stars: Int = 0,
    var remainingAnswers: List<String> = emptyList(),
    val id: String = generateRandomUUIDString(),
    var falehFeel: FalehFeel = FalehFeel.IDLE,
)