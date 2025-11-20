package studio.s98.landingpagegame.board

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toIntRect
import landingpagegame.composeapp.generated.resources.Res
import landingpagegame.composeapp.generated.resources.pause_btn
import org.jetbrains.compose.resources.painterResource
import studio.s98.landingpagegame.util.detectMyDragGesturesAfterLongPress
import studio.s98.landingpagegame.util.mainBackground
import studio.s98.landingpagegame.models.Letter
import kotlin.collections.plus

@Composable
fun BoardScreen(
    isFirst: Boolean,
    toResult: (starsCount: Int, missingWords: List<String>) -> Unit,
) {

    val viewModel = remember { BoardViewModel() }
    val uiState by viewModel.state.collectAsState()

    DisposableEffect(key1 = Unit) {
        onDispose {
//            viewModel.release()
        }
    }
    var isPause by remember {
        mutableStateOf(false)
    }
    val showTutorial = remember {
        mutableStateOf(isFirst)
    }
    LaunchedEffect(key1 = Unit, block = {
        if (!isFirst)
            viewModel.onEvent(BoardEvents.GameStarted)
    })


//    BackHandler(enabled = false) {}



    LaunchedEffect(uiState.isNavigate) {
        if (uiState.isNavigate) {
            toResult(uiState.stars, uiState.remainingAnswers)
        }
    }

    Box {
//        if (showTutorial.value)
//            TutorialScreen {
//                showTutorial.value = false
//                viewModel.onEvent(BoardEvents.GameStarted)
//            }
//        if (isPause)
//            PauseScreen(onBackToHome = {
//                viewModel.onEvent(BoardEvents.OnCanel)
//                isPause = false
//                navHostController.popBackStack()
//            }) {
//                viewModel.onEvent(BoardEvents.OnResume)
//                isPause = false
//            }

        BoardScreenContent(list = uiState.grid,
            points = uiState.points,
            percent = uiState.percent,
            time = uiState.time,
            feels = uiState.falehFeel,
            onPause = {
                viewModel.onEvent(BoardEvents.OnPause)
                isPause = true
            },
            onUserSwiped = { ids ->
                viewModel.onEvent(BoardEvents.LetterSwipedWithLetterInformation(ids))
            })
    }
    LaunchedEffect(key1 = Unit) {
//        viewModel.soundStateListener()
    }


}

@Composable
private fun BoardScreenContent(
    list: List<List<Letter>>,
    points: Int,
    time: String,
    feels: FalehFeel,
    percent: Float,
    onPause: () -> Unit,
    onUserSwiped: (Set<Int>) -> Unit
) {
    val gridState = rememberLazyGridState()

    val selectedIds = rememberSaveable { mutableStateOf(emptySet<Int>()) }

    Column(
        modifier = Modifier
            .background(mainBackground)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            TimerWidget(percent = percent, time = time)
            PointsWidget(points)
            Image(
                modifier = Modifier.clickable {
                    onPause()
                },
                painter = painterResource(resource = Res.drawable.pause_btn),
                contentDescription = "Pause button"
            )

        }
        Text(
            text = "كل حرف يمثل 50 نقطة",
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xff9F4FC0),
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .offset(y = (30).dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {


            LazyVerticalGrid(
                state = gridState,
                columns = GridCells.Fixed(4),
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.pointerInput(Unit) {
                    var initialLetterId: Int?
                    var currentLetterId: Int? = null
                    detectMyDragGesturesAfterLongPress(

                        onDragStart = { offset ->
                            letterIdAtOffset(offset, gridState)?.let { index ->
                                if (!selectedIds.value.contains(index)) {
                                    initialLetterId = index
                                    currentLetterId = initialLetterId
                                    selectedIds.value += index
                                }
                            }
                        },
                        onDragCancel = {
                            initialLetterId = null
                        },
                        onDragEnd = {
                            initialLetterId = null
                            onUserSwiped(selectedIds.value)
                            selectedIds.value = emptySet()
                        },
                        onDrag = { change, _ ->
                            letterIdAtOffset(change.position, gridState)?.let { index ->
                                if (currentLetterId != index) {
                                    currentLetterId = index
                                    selectedIds.value += index
                                }
                            }
                        }
                    )
                },
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalArrangement = Arrangement.spacedBy(
                    20.dp
                )
            ) {
                items(list.flatten().size, key = { it }) { index ->
                    val selectId =
                        remember { derivedStateOf { index in selectedIds.value } }
                    BoardTileWidget(
                        modifier = Modifier,
                        isOdd = index % 2 == 0,
                        letter = list.flatten()[index],
                        isSelected = selectId.value,
                        textSize = 30
                    )
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))

//        AnimatedFrames(modifier = Modifier.size(172.dp), feels)
        Text(
            text = "شعور التسعيني",
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.offset(y = (-40).dp),
            color = Color(0xff9F4FC0),
            fontSize = 20.sp
        )

    }

}
//
//@Preview
//@Composable
//private fun BoardScreenPreview() {
//    val alphabet = ('A'..'Z').toList() // List of letters from A to Z
//    val dummyList = List(20) {
//        Letter(letter = alphabet.random().toString())
//    }
//    BoardScreenContent(
//        list = dummyList.chunked(4),
//        points = 500,
//        time = "1:00",
//        onPause = {},
//        percent = 50f,
//        feels = FalehFeel.IDLE
//    ) {
//
//    }
//}


fun letterIdAtOffset(hitPoint: Offset, state: LazyGridState): Int? =
    state.layoutInfo.visibleItemsInfo.find { itemInfo ->
        itemInfo.size.toIntRect().contains(hitPoint.round() - itemInfo.offset)
    }?.key as? Int


