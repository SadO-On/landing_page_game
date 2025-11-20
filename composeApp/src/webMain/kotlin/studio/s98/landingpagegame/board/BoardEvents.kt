package studio.s98.landingpagegame.board

sealed class BoardEvents {

    data object UserSwiped: BoardEvents()
    data class LetterSwiped(val positions: List<Int>): BoardEvents()
    data class LetterSwipedWithLetterInformation(val letter: Set<Int>): BoardEvents()
    data object GameStarted: BoardEvents()
    data object OnPause: BoardEvents()
    data object OnResume: BoardEvents()
    data object OnCancel: BoardEvents()
}