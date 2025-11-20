package studio.s98.landingpagegame.board

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import io.github.aakira.napier.Napier
import kotlinx.coroutines.delay
import landingpagegame.composeapp.generated.resources.Res
import landingpagegame.composeapp.generated.resources.idle_frame1
import landingpagegame.composeapp.generated.resources.idle_frame2
import landingpagegame.composeapp.generated.resources.idle_frame3
import landingpagegame.composeapp.generated.resources.idle_frame4
import landingpagegame.composeapp.generated.resources.idle_frame5
import landingpagegame.composeapp.generated.resources.idle_frame6
import landingpagegame.composeapp.generated.resources.idle_frame7
import landingpagegame.composeapp.generated.resources.idle_frame8
import landingpagegame.composeapp.generated.resources.idle_frame9
import landingpagegame.composeapp.generated.resources.idle_frame10
import landingpagegame.composeapp.generated.resources.idle_frame11
import landingpagegame.composeapp.generated.resources.idle_frame12
import landingpagegame.composeapp.generated.resources.idle_frame13
import landingpagegame.composeapp.generated.resources.idle_frame14
import landingpagegame.composeapp.generated.resources.idle_frame15
import landingpagegame.composeapp.generated.resources.idle_frame16
import landingpagegame.composeapp.generated.resources.idle_frame17
import landingpagegame.composeapp.generated.resources.idle_frame18
import landingpagegame.composeapp.generated.resources.idle_frame19
import landingpagegame.composeapp.generated.resources.idle_frame20
import landingpagegame.composeapp.generated.resources.idle_frame21
import landingpagegame.composeapp.generated.resources.idle_frame22
import landingpagegame.composeapp.generated.resources.idle_frame23
import landingpagegame.composeapp.generated.resources.idle_frame24
import landingpagegame.composeapp.generated.resources.idle_frame25
import landingpagegame.composeapp.generated.resources.idle_frame26
import landingpagegame.composeapp.generated.resources.idle_frame27
import landingpagegame.composeapp.generated.resources.idle_frame28
import landingpagegame.composeapp.generated.resources.idle_frame29
import landingpagegame.composeapp.generated.resources.idle_frame30
import landingpagegame.composeapp.generated.resources.idle_frame31
import landingpagegame.composeapp.generated.resources.idle_frame32
import landingpagegame.composeapp.generated.resources.idle_frame33
import landingpagegame.composeapp.generated.resources.idle_frame34
import landingpagegame.composeapp.generated.resources.idle_frame35
import landingpagegame.composeapp.generated.resources.idle_frame36
import landingpagegame.composeapp.generated.resources.idle_frame37
import landingpagegame.composeapp.generated.resources.idle_frame38
import landingpagegame.composeapp.generated.resources.idle_frame39
import landingpagegame.composeapp.generated.resources.idle_frame40
import landingpagegame.composeapp.generated.resources.idle_frame41
import landingpagegame.composeapp.generated.resources.idle_frame42
import landingpagegame.composeapp.generated.resources.idle_frame43
import landingpagegame.composeapp.generated.resources.idle_frame44
import landingpagegame.composeapp.generated.resources.idle_frame45
import landingpagegame.composeapp.generated.resources.idle_frame46
import landingpagegame.composeapp.generated.resources.idle_frame47
import landingpagegame.composeapp.generated.resources.idle_frame48
import landingpagegame.composeapp.generated.resources.idle_frame49
import landingpagegame.composeapp.generated.resources.idle_frame50
import landingpagegame.composeapp.generated.resources.idle_frame51
import landingpagegame.composeapp.generated.resources.idle_frame52
import landingpagegame.composeapp.generated.resources.idle_frame53
import landingpagegame.composeapp.generated.resources.idle_frame54
import landingpagegame.composeapp.generated.resources.idle_frame55
import landingpagegame.composeapp.generated.resources.idle_frame56
import landingpagegame.composeapp.generated.resources.idle_frame57
import landingpagegame.composeapp.generated.resources.idle_frame58
import landingpagegame.composeapp.generated.resources.idle_frame59
import landingpagegame.composeapp.generated.resources.idle_frame60
import landingpagegame.composeapp.generated.resources.idle_frame61
import landingpagegame.composeapp.generated.resources.idle_frame62
import landingpagegame.composeapp.generated.resources.idle_frame63
import landingpagegame.composeapp.generated.resources.idle_frame64
import landingpagegame.composeapp.generated.resources.idle_frame65
import landingpagegame.composeapp.generated.resources.idle_frame66
import landingpagegame.composeapp.generated.resources.idle_frame67
import landingpagegame.composeapp.generated.resources.idle_frame68
import landingpagegame.composeapp.generated.resources.idle_frame69
import landingpagegame.composeapp.generated.resources.idle_frame70
import landingpagegame.composeapp.generated.resources.idle_frame71
import landingpagegame.composeapp.generated.resources.idle_frame72
import landingpagegame.composeapp.generated.resources.idle_frame73
import landingpagegame.composeapp.generated.resources.idle_frame74
import landingpagegame.composeapp.generated.resources.idle_frame75
import landingpagegame.composeapp.generated.resources.idle_frame76
import landingpagegame.composeapp.generated.resources.idle_frame77
import landingpagegame.composeapp.generated.resources.idle_frame78
import landingpagegame.composeapp.generated.resources.idle_frame79
import landingpagegame.composeapp.generated.resources.idle_frame80
import landingpagegame.composeapp.generated.resources.idle_frame81
import landingpagegame.composeapp.generated.resources.idle_frame82
import landingpagegame.composeapp.generated.resources.idle_frame83
import landingpagegame.composeapp.generated.resources.idle_frame84
import landingpagegame.composeapp.generated.resources.idle_frame85
import landingpagegame.composeapp.generated.resources.idle_frame86
import landingpagegame.composeapp.generated.resources.idle_frame87
import landingpagegame.composeapp.generated.resources.idle_frame88
import landingpagegame.composeapp.generated.resources.idle_frame89
import landingpagegame.composeapp.generated.resources.idle_frame90
import landingpagegame.composeapp.generated.resources.idle_frame91
import landingpagegame.composeapp.generated.resources.idle_frame92
import landingpagegame.composeapp.generated.resources.idle_frame93
import landingpagegame.composeapp.generated.resources.idle_frame94
import landingpagegame.composeapp.generated.resources.idle_frame95
import landingpagegame.composeapp.generated.resources.idle_frame96
import landingpagegame.composeapp.generated.resources.idle_frame97
import landingpagegame.composeapp.generated.resources.idle_frame98
import landingpagegame.composeapp.generated.resources.idle_frame99
import landingpagegame.composeapp.generated.resources.idle_frame100
import landingpagegame.composeapp.generated.resources.idle_frame101
import landingpagegame.composeapp.generated.resources.idle_frame102
import landingpagegame.composeapp.generated.resources.idle_frame103
import landingpagegame.composeapp.generated.resources.idle_frame104
import landingpagegame.composeapp.generated.resources.idle_frame105
import landingpagegame.composeapp.generated.resources.idle_frame106
import landingpagegame.composeapp.generated.resources.idle_frame107
import landingpagegame.composeapp.generated.resources.idle_frame108
import landingpagegame.composeapp.generated.resources.idle_frame109
import landingpagegame.composeapp.generated.resources.idle_frame110
import landingpagegame.composeapp.generated.resources.idle_frame111
import landingpagegame.composeapp.generated.resources.idle_frame112
import landingpagegame.composeapp.generated.resources.idle_frame113
import landingpagegame.composeapp.generated.resources.idle_frame114
import landingpagegame.composeapp.generated.resources.idle_frame115
import landingpagegame.composeapp.generated.resources.idle_frame116
import landingpagegame.composeapp.generated.resources.idle_frame117
import landingpagegame.composeapp.generated.resources.idle_frame118
import landingpagegame.composeapp.generated.resources.idle_frame119
import landingpagegame.composeapp.generated.resources.idle_frame120
import landingpagegame.composeapp.generated.resources.idle_frame121
import landingpagegame.composeapp.generated.resources.idle_frame122
import landingpagegame.composeapp.generated.resources.idle_frame123
import landingpagegame.composeapp.generated.resources.idle_frame124
import landingpagegame.composeapp.generated.resources.idle_frame125
import landingpagegame.composeapp.generated.resources.idle_frame126
import landingpagegame.composeapp.generated.resources.idle_frame127
import landingpagegame.composeapp.generated.resources.idle_frame128
import landingpagegame.composeapp.generated.resources.idle_frame129
import landingpagegame.composeapp.generated.resources.idle_frame130
import landingpagegame.composeapp.generated.resources.idle_frame131
import landingpagegame.composeapp.generated.resources.idle_frame132
import landingpagegame.composeapp.generated.resources.idle_frame133
import landingpagegame.composeapp.generated.resources.idle_frame134
import landingpagegame.composeapp.generated.resources.idle_frame135
import landingpagegame.composeapp.generated.resources.idle_frame136
import landingpagegame.composeapp.generated.resources.idle_frame137
import landingpagegame.composeapp.generated.resources.idle_frame138
import landingpagegame.composeapp.generated.resources.idle_frame139
import landingpagegame.composeapp.generated.resources.idle_frame140
import landingpagegame.composeapp.generated.resources.idle_frame141
import landingpagegame.composeapp.generated.resources.idle_frame142
import landingpagegame.composeapp.generated.resources.idle_frame143
import landingpagegame.composeapp.generated.resources.idle_frame144
import landingpagegame.composeapp.generated.resources.idle_frame145
import landingpagegame.composeapp.generated.resources.idle_frame146
import landingpagegame.composeapp.generated.resources.idle_frame147
import landingpagegame.composeapp.generated.resources.idle_frame148
import landingpagegame.composeapp.generated.resources.idle_frame149
import landingpagegame.composeapp.generated.resources.idle_frame150
import landingpagegame.composeapp.generated.resources.idle_frame151
import landingpagegame.composeapp.generated.resources.idle_frame152
import landingpagegame.composeapp.generated.resources.idle_frame153
import landingpagegame.composeapp.generated.resources.idle_frame154
import landingpagegame.composeapp.generated.resources.idle_frame155
import landingpagegame.composeapp.generated.resources.idle_frame156
import landingpagegame.composeapp.generated.resources.idle_frame157
import landingpagegame.composeapp.generated.resources.idle_frame158
import landingpagegame.composeapp.generated.resources.idle_frame159
import landingpagegame.composeapp.generated.resources.idle_frame160
import landingpagegame.composeapp.generated.resources.idle_frame161
import landingpagegame.composeapp.generated.resources.idle_frame162
import landingpagegame.composeapp.generated.resources.idle_frame163
import landingpagegame.composeapp.generated.resources.idle_frame164
import landingpagegame.composeapp.generated.resources.idle_frame165
import landingpagegame.composeapp.generated.resources.idle_frame166
import landingpagegame.composeapp.generated.resources.idle_frame167
import landingpagegame.composeapp.generated.resources.idle_frame168
import landingpagegame.composeapp.generated.resources.idle_frame169
import landingpagegame.composeapp.generated.resources.idle_frame170
import landingpagegame.composeapp.generated.resources.idle_frame171
import landingpagegame.composeapp.generated.resources.idle_frame172
import landingpagegame.composeapp.generated.resources.idle_frame173
import landingpagegame.composeapp.generated.resources.idle_frame174
import landingpagegame.composeapp.generated.resources.idle_frame175
import landingpagegame.composeapp.generated.resources.idle_frame176
import landingpagegame.composeapp.generated.resources.idle_frame177
import landingpagegame.composeapp.generated.resources.idle_frame178
import landingpagegame.composeapp.generated.resources.idle_frame179
import landingpagegame.composeapp.generated.resources.idle_frame180
import landingpagegame.composeapp.generated.resources.idle_frame181
import landingpagegame.composeapp.generated.resources.idle_frame182
import landingpagegame.composeapp.generated.resources.idle_frame183
import landingpagegame.composeapp.generated.resources.idle_frame184
import landingpagegame.composeapp.generated.resources.idle_frame185
import landingpagegame.composeapp.generated.resources.idle_frame186
import landingpagegame.composeapp.generated.resources.idle_frame187
import landingpagegame.composeapp.generated.resources.idle_frame188
import landingpagegame.composeapp.generated.resources.idle_frame189
import landingpagegame.composeapp.generated.resources.idle_frame190
import landingpagegame.composeapp.generated.resources.idle_frame191
import landingpagegame.composeapp.generated.resources.idle_frame192
import landingpagegame.composeapp.generated.resources.right_answer1
import landingpagegame.composeapp.generated.resources.right_answer10
import kotlin.collections.listOf
import landingpagegame.composeapp.generated.resources.sleep_frame1
import landingpagegame.composeapp.generated.resources.sleep_frame2
import landingpagegame.composeapp.generated.resources.sleep_frame3
import landingpagegame.composeapp.generated.resources.sleep_frame4
import landingpagegame.composeapp.generated.resources.sleep_frame5
import landingpagegame.composeapp.generated.resources.sleep_frame6
import landingpagegame.composeapp.generated.resources.sleep_frame7
import landingpagegame.composeapp.generated.resources.sleep_frame8
import landingpagegame.composeapp.generated.resources.sleep_frame9
import landingpagegame.composeapp.generated.resources.sleep_frame10
import landingpagegame.composeapp.generated.resources.sleep_frame11
import landingpagegame.composeapp.generated.resources.sleep_frame12
import landingpagegame.composeapp.generated.resources.sleep_frame13
import landingpagegame.composeapp.generated.resources.sleep_frame14
import landingpagegame.composeapp.generated.resources.sleep_frame15
import landingpagegame.composeapp.generated.resources.sleep_frame16
import landingpagegame.composeapp.generated.resources.sleep_frame17
import landingpagegame.composeapp.generated.resources.sleep_frame18
import landingpagegame.composeapp.generated.resources.sleep_frame19
import landingpagegame.composeapp.generated.resources.sleep_frame20
import landingpagegame.composeapp.generated.resources.sleep_frame21
import landingpagegame.composeapp.generated.resources.sleep_frame22
import landingpagegame.composeapp.generated.resources.sleep_frame23
import landingpagegame.composeapp.generated.resources.sleep_frame24
import landingpagegame.composeapp.generated.resources.sleep_frame25
import landingpagegame.composeapp.generated.resources.sleep_frame26
import landingpagegame.composeapp.generated.resources.sleep_frame27
import landingpagegame.composeapp.generated.resources.sleep_frame28
import landingpagegame.composeapp.generated.resources.sleep_frame29
import landingpagegame.composeapp.generated.resources.sleep_frame30
import landingpagegame.composeapp.generated.resources.sleep_frame31
import landingpagegame.composeapp.generated.resources.sleep_frame32
import landingpagegame.composeapp.generated.resources.sleep_frame33
import landingpagegame.composeapp.generated.resources.sleep_frame34
import landingpagegame.composeapp.generated.resources.sleep_frame35
import landingpagegame.composeapp.generated.resources.sleep_frame36
import landingpagegame.composeapp.generated.resources.sleep_frame37
import landingpagegame.composeapp.generated.resources.sleep_frame38
import landingpagegame.composeapp.generated.resources.sleep_frame39
import landingpagegame.composeapp.generated.resources.sleep_frame40
import landingpagegame.composeapp.generated.resources.sleep_frame41
import landingpagegame.composeapp.generated.resources.sleep_frame42
import landingpagegame.composeapp.generated.resources.sleep_frame43
import landingpagegame.composeapp.generated.resources.sleep_frame44
import landingpagegame.composeapp.generated.resources.sleep_frame45
import landingpagegame.composeapp.generated.resources.sleep_frame46
import landingpagegame.composeapp.generated.resources.sleep_frame47
import landingpagegame.composeapp.generated.resources.sleep_frame48
import landingpagegame.composeapp.generated.resources.sleep_frame49
import landingpagegame.composeapp.generated.resources.sleep_frame50
import landingpagegame.composeapp.generated.resources.sleep_frame51
import landingpagegame.composeapp.generated.resources.sleep_frame52
import landingpagegame.composeapp.generated.resources.sleep_frame53
import landingpagegame.composeapp.generated.resources.sleep_frame54
import landingpagegame.composeapp.generated.resources.sleep_frame55
import landingpagegame.composeapp.generated.resources.sleep_frame56
import landingpagegame.composeapp.generated.resources.sleep_frame57
import landingpagegame.composeapp.generated.resources.sleep_frame58
import landingpagegame.composeapp.generated.resources.sleep_frame59
import landingpagegame.composeapp.generated.resources.sleep_frame60
import landingpagegame.composeapp.generated.resources.sleep_frame61
import landingpagegame.composeapp.generated.resources.sleep_frame62
import landingpagegame.composeapp.generated.resources.sleep_frame63
import landingpagegame.composeapp.generated.resources.sleep_frame64
import landingpagegame.composeapp.generated.resources.sleep_frame65
import landingpagegame.composeapp.generated.resources.sleep_frame66
import landingpagegame.composeapp.generated.resources.sleep_frame67
import landingpagegame.composeapp.generated.resources.sleep_frame68
import landingpagegame.composeapp.generated.resources.sleep_frame69
import landingpagegame.composeapp.generated.resources.sleep_frame70
import landingpagegame.composeapp.generated.resources.sleep_frame71
import landingpagegame.composeapp.generated.resources.sleep_frame72
import landingpagegame.composeapp.generated.resources.sleep_frame73
import landingpagegame.composeapp.generated.resources.sleep_frame74
import landingpagegame.composeapp.generated.resources.sleep_frame75
import landingpagegame.composeapp.generated.resources.sleep_frame76
import landingpagegame.composeapp.generated.resources.sleep_frame77
import landingpagegame.composeapp.generated.resources.sleep_frame78
import landingpagegame.composeapp.generated.resources.sleep_frame79
import landingpagegame.composeapp.generated.resources.sleep_frame80
import landingpagegame.composeapp.generated.resources.sleep_frame81
import landingpagegame.composeapp.generated.resources.sleep_frame82
import landingpagegame.composeapp.generated.resources.sleep_frame83
import landingpagegame.composeapp.generated.resources.sleep_frame84
import landingpagegame.composeapp.generated.resources.sleep_frame85
import landingpagegame.composeapp.generated.resources.sleep_frame86
import landingpagegame.composeapp.generated.resources.sleep_frame87
import landingpagegame.composeapp.generated.resources.sleep_frame88
import landingpagegame.composeapp.generated.resources.sleep_frame89
import landingpagegame.composeapp.generated.resources.sleep_frame90
import landingpagegame.composeapp.generated.resources.sleep_frame91
import landingpagegame.composeapp.generated.resources.sleep_frame92
import landingpagegame.composeapp.generated.resources.sleep_frame93
import landingpagegame.composeapp.generated.resources.sleep_frame94
import landingpagegame.composeapp.generated.resources.sleep_frame95
import landingpagegame.composeapp.generated.resources.sleep_frame96
import landingpagegame.composeapp.generated.resources.sleep_frame97
import landingpagegame.composeapp.generated.resources.sleep_frame98
import landingpagegame.composeapp.generated.resources.sleep_frame99
import landingpagegame.composeapp.generated.resources.sleep_frame100
import landingpagegame.composeapp.generated.resources.sleep_frame101
import landingpagegame.composeapp.generated.resources.sleep_frame102
import landingpagegame.composeapp.generated.resources.sleep_frame103
import landingpagegame.composeapp.generated.resources.sleep_frame104
import landingpagegame.composeapp.generated.resources.sleep_frame105
import landingpagegame.composeapp.generated.resources.sleep_frame106
import landingpagegame.composeapp.generated.resources.sleep_frame107
import landingpagegame.composeapp.generated.resources.sleep_frame108
import landingpagegame.composeapp.generated.resources.sleep_frame109
import landingpagegame.composeapp.generated.resources.sleep_frame110
import landingpagegame.composeapp.generated.resources.sleep_frame111
import landingpagegame.composeapp.generated.resources.sleep_frame112
import landingpagegame.composeapp.generated.resources.sleep_frame113
import landingpagegame.composeapp.generated.resources.sleep_frame114
import landingpagegame.composeapp.generated.resources.sleep_frame115
import landingpagegame.composeapp.generated.resources.sleep_frame116
import landingpagegame.composeapp.generated.resources.sleep_frame117
import landingpagegame.composeapp.generated.resources.sleep_frame118
import landingpagegame.composeapp.generated.resources.sleep_frame119
import landingpagegame.composeapp.generated.resources.sleep_frame120
import landingpagegame.composeapp.generated.resources.sleep_frame121
import landingpagegame.composeapp.generated.resources.sleep_frame122
import landingpagegame.composeapp.generated.resources.sleep_frame123
import landingpagegame.composeapp.generated.resources.sleep_frame124
import landingpagegame.composeapp.generated.resources.sleep_frame125
import landingpagegame.composeapp.generated.resources.sleep_frame126
import landingpagegame.composeapp.generated.resources.sleep_frame127
import landingpagegame.composeapp.generated.resources.sleep_frame128
import landingpagegame.composeapp.generated.resources.sleep_frame129
import landingpagegame.composeapp.generated.resources.sleep_frame130
import landingpagegame.composeapp.generated.resources.sleep_frame131
import landingpagegame.composeapp.generated.resources.sleep_frame132
import landingpagegame.composeapp.generated.resources.sleep_frame133
import landingpagegame.composeapp.generated.resources.sleep_frame134
import landingpagegame.composeapp.generated.resources.sleep_frame135
import landingpagegame.composeapp.generated.resources.sleep_frame136
import landingpagegame.composeapp.generated.resources.sleep_frame137
import landingpagegame.composeapp.generated.resources.sleep_frame138
import landingpagegame.composeapp.generated.resources.sleep_frame139
import landingpagegame.composeapp.generated.resources.sleep_frame140
import landingpagegame.composeapp.generated.resources.sleep_frame141
import landingpagegame.composeapp.generated.resources.sleep_frame142
import landingpagegame.composeapp.generated.resources.sleep_frame143
import landingpagegame.composeapp.generated.resources.sleep_frame144
import landingpagegame.composeapp.generated.resources.sleep_frame145
import landingpagegame.composeapp.generated.resources.sleep_frame146
import landingpagegame.composeapp.generated.resources.sleep_frame147
import landingpagegame.composeapp.generated.resources.sleep_frame148
import landingpagegame.composeapp.generated.resources.sleep_frame149
import landingpagegame.composeapp.generated.resources.sleep_frame150
import landingpagegame.composeapp.generated.resources.sleep_frame151
import landingpagegame.composeapp.generated.resources.sleep_frame152
import landingpagegame.composeapp.generated.resources.sleep_frame153
import landingpagegame.composeapp.generated.resources.sleep_frame154
import landingpagegame.composeapp.generated.resources.sleep_frame155
import landingpagegame.composeapp.generated.resources.sleep_frame156
import landingpagegame.composeapp.generated.resources.sleep_frame157
import landingpagegame.composeapp.generated.resources.sleep_frame158
import landingpagegame.composeapp.generated.resources.sleep_frame159
import landingpagegame.composeapp.generated.resources.sleep_frame160
import landingpagegame.composeapp.generated.resources.sleep_frame161
import landingpagegame.composeapp.generated.resources.sleep_frame162
import landingpagegame.composeapp.generated.resources.sleep_frame163
import landingpagegame.composeapp.generated.resources.sleep_frame164
import landingpagegame.composeapp.generated.resources.sleep_frame165
import landingpagegame.composeapp.generated.resources.sleep_frame166
import landingpagegame.composeapp.generated.resources.sleep_frame167
import landingpagegame.composeapp.generated.resources.sleep_frame168
import landingpagegame.composeapp.generated.resources.sleep_frame169
import landingpagegame.composeapp.generated.resources.sleep_frame170
import landingpagegame.composeapp.generated.resources.sleep_frame171
import landingpagegame.composeapp.generated.resources.sleep_frame172
import landingpagegame.composeapp.generated.resources.sleep_frame173
import landingpagegame.composeapp.generated.resources.sleep_frame174
import landingpagegame.composeapp.generated.resources.sleep_frame175
import landingpagegame.composeapp.generated.resources.sleep_frame176
import landingpagegame.composeapp.generated.resources.sleep_frame177
import landingpagegame.composeapp.generated.resources.sleep_frame178
import landingpagegame.composeapp.generated.resources.sleep_frame179
import landingpagegame.composeapp.generated.resources.sleep_frame180
import landingpagegame.composeapp.generated.resources.sleep_frame181
import landingpagegame.composeapp.generated.resources.sleep_frame182
import landingpagegame.composeapp.generated.resources.sleep_frame183
import landingpagegame.composeapp.generated.resources.sleep_frame184
import landingpagegame.composeapp.generated.resources.sleep_frame185
import landingpagegame.composeapp.generated.resources.sleep_frame186
import landingpagegame.composeapp.generated.resources.sleep_frame187
import landingpagegame.composeapp.generated.resources.sleep_frame188
import landingpagegame.composeapp.generated.resources.sleep_frame189
import landingpagegame.composeapp.generated.resources.sleep_frame190
import landingpagegame.composeapp.generated.resources.sleep_frame191
import landingpagegame.composeapp.generated.resources.sleep_frame192
import landingpagegame.composeapp.generated.resources.sleep_frame193
import landingpagegame.composeapp.generated.resources.sleep_frame194
import landingpagegame.composeapp.generated.resources.sleep_frame195
import landingpagegame.composeapp.generated.resources.sleep_frame196
import landingpagegame.composeapp.generated.resources.sleep_frame197
import landingpagegame.composeapp.generated.resources.sleep_frame198
import landingpagegame.composeapp.generated.resources.sleep_frame199
import landingpagegame.composeapp.generated.resources.sleep_frame200
import landingpagegame.composeapp.generated.resources.sleep_frame201
import landingpagegame.composeapp.generated.resources.sleep_frame202
import landingpagegame.composeapp.generated.resources.sleep_frame203
import landingpagegame.composeapp.generated.resources.sleep_frame204
import landingpagegame.composeapp.generated.resources.sleep_frame205
import landingpagegame.composeapp.generated.resources.sleep_frame206
import landingpagegame.composeapp.generated.resources.sleep_frame207
import landingpagegame.composeapp.generated.resources.sleep_frame208
import landingpagegame.composeapp.generated.resources.sleep_frame209
import landingpagegame.composeapp.generated.resources.sleep_frame210
import landingpagegame.composeapp.generated.resources.sleep_frame211
import landingpagegame.composeapp.generated.resources.sleep_frame212
import landingpagegame.composeapp.generated.resources.sleep_frame213
import landingpagegame.composeapp.generated.resources.sleep_frame214
import landingpagegame.composeapp.generated.resources.right_answer2
import landingpagegame.composeapp.generated.resources.right_answer3
import landingpagegame.composeapp.generated.resources.right_answer4
import landingpagegame.composeapp.generated.resources.right_answer5
import landingpagegame.composeapp.generated.resources.right_answer6
import landingpagegame.composeapp.generated.resources.right_answer7
import landingpagegame.composeapp.generated.resources.right_answer8
import landingpagegame.composeapp.generated.resources.right_answer9
import landingpagegame.composeapp.generated.resources.right_answer11
import landingpagegame.composeapp.generated.resources.right_answer12
import landingpagegame.composeapp.generated.resources.right_answer13
import landingpagegame.composeapp.generated.resources.right_answer14
import landingpagegame.composeapp.generated.resources.right_answer15
import landingpagegame.composeapp.generated.resources.right_answer16
import landingpagegame.composeapp.generated.resources.right_answer17
import landingpagegame.composeapp.generated.resources.right_answer18
import landingpagegame.composeapp.generated.resources.right_answer19
import landingpagegame.composeapp.generated.resources.right_answer20
import landingpagegame.composeapp.generated.resources.right_answer21
import landingpagegame.composeapp.generated.resources.right_answer22
import landingpagegame.composeapp.generated.resources.right_answer23
import landingpagegame.composeapp.generated.resources.right_answer24
import landingpagegame.composeapp.generated.resources.right_answer25
import landingpagegame.composeapp.generated.resources.right_answer26
import landingpagegame.composeapp.generated.resources.right_answer27
import landingpagegame.composeapp.generated.resources.right_answer28
import landingpagegame.composeapp.generated.resources.right_answer29
import landingpagegame.composeapp.generated.resources.right_answer30
import landingpagegame.composeapp.generated.resources.right_answer31
import landingpagegame.composeapp.generated.resources.right_answer32
import landingpagegame.composeapp.generated.resources.right_answer33
import landingpagegame.composeapp.generated.resources.right_answer34
import landingpagegame.composeapp.generated.resources.right_answer35
import landingpagegame.composeapp.generated.resources.right_answer36
import landingpagegame.composeapp.generated.resources.right_answer37
import landingpagegame.composeapp.generated.resources.right_answer38
import landingpagegame.composeapp.generated.resources.right_answer39
import landingpagegame.composeapp.generated.resources.right_answer40
import landingpagegame.composeapp.generated.resources.right_answer41
import landingpagegame.composeapp.generated.resources.right_answer42
import landingpagegame.composeapp.generated.resources.right_answer43
import landingpagegame.composeapp.generated.resources.right_answer44
import landingpagegame.composeapp.generated.resources.right_answer45
import landingpagegame.composeapp.generated.resources.right_answer46
import landingpagegame.composeapp.generated.resources.right_answer47
import landingpagegame.composeapp.generated.resources.right_answer48
import landingpagegame.composeapp.generated.resources.right_answer49
import landingpagegame.composeapp.generated.resources.right_answer50
import landingpagegame.composeapp.generated.resources.right_answer51
import landingpagegame.composeapp.generated.resources.right_answer52
import landingpagegame.composeapp.generated.resources.right_answer53
import landingpagegame.composeapp.generated.resources.right_answer54
import landingpagegame.composeapp.generated.resources.right_answer55
import landingpagegame.composeapp.generated.resources.right_answer56
import landingpagegame.composeapp.generated.resources.right_answer57
import landingpagegame.composeapp.generated.resources.right_answer58
import landingpagegame.composeapp.generated.resources.right_answer59
import landingpagegame.composeapp.generated.resources.right_answer60
import landingpagegame.composeapp.generated.resources.right_answer61
import landingpagegame.composeapp.generated.resources.right_answer62
import landingpagegame.composeapp.generated.resources.right_answer63
import landingpagegame.composeapp.generated.resources.right_answer64
import landingpagegame.composeapp.generated.resources.right_answer65
import landingpagegame.composeapp.generated.resources.right_answer66
import landingpagegame.composeapp.generated.resources.right_answer67
import landingpagegame.composeapp.generated.resources.right_answer68
import landingpagegame.composeapp.generated.resources.right_answer69
import landingpagegame.composeapp.generated.resources.right_answer70
import landingpagegame.composeapp.generated.resources.right_answer71
import landingpagegame.composeapp.generated.resources.right_answer72
import landingpagegame.composeapp.generated.resources.right_answer73
import landingpagegame.composeapp.generated.resources.right_answer74
import landingpagegame.composeapp.generated.resources.right_answer75
import landingpagegame.composeapp.generated.resources.right_answer76
import landingpagegame.composeapp.generated.resources.right_answer77
import landingpagegame.composeapp.generated.resources.right_answer78
import landingpagegame.composeapp.generated.resources.right_answer79
import landingpagegame.composeapp.generated.resources.right_answer80
import landingpagegame.composeapp.generated.resources.sleep_frame1
import landingpagegame.composeapp.generated.resources.sleep_frame100
import org.jetbrains.compose.resources.painterResource

@Composable
fun AnimatedFrames(modifier: Modifier, feel: FalehFeel) {

    Napier.i(feel.name)

    val frames = remember { mutableIntStateOf(IDLE) }
    val duration = remember { mutableIntStateOf(IDLE_TIME) }
    val frameName = remember { mutableStateOf(IDLE_NAME) }

    val idleFrames = remember {
        listOf(
            Res.drawable.idle_frame1,
            Res.drawable.idle_frame2,
            Res.drawable.idle_frame3,
            Res.drawable.idle_frame4,
            Res.drawable.idle_frame5,
            Res.drawable.idle_frame6,
            Res.drawable.idle_frame7,
            Res.drawable.idle_frame8,
            Res.drawable.idle_frame9,
            Res.drawable.idle_frame10,
            Res.drawable.idle_frame11,
            Res.drawable.idle_frame12,
            Res.drawable.idle_frame13,
            Res.drawable.idle_frame14,
            Res.drawable.idle_frame15,
            Res.drawable.idle_frame16,
            Res.drawable.idle_frame17,
            Res.drawable.idle_frame18,
            Res.drawable.idle_frame19,
            Res.drawable.idle_frame20,
            Res.drawable.idle_frame21,
            Res.drawable.idle_frame22,
            Res.drawable.idle_frame23,
            Res.drawable.idle_frame24,
            Res.drawable.idle_frame25,
            Res.drawable.idle_frame26,
            Res.drawable.idle_frame27,
            Res.drawable.idle_frame28,
            Res.drawable.idle_frame29,
            Res.drawable.idle_frame30,
            Res.drawable.idle_frame31,
            Res.drawable.idle_frame32,
            Res.drawable.idle_frame33,
            Res.drawable.idle_frame34,
            Res.drawable.idle_frame35,
            Res.drawable.idle_frame36,
            Res.drawable.idle_frame37,
            Res.drawable.idle_frame38,
            Res.drawable.idle_frame39,
            Res.drawable.idle_frame40,
            Res.drawable.idle_frame41,
            Res.drawable.idle_frame42,
            Res.drawable.idle_frame43,
            Res.drawable.idle_frame44,
            Res.drawable.idle_frame45,
            Res.drawable.idle_frame46,
            Res.drawable.idle_frame47,
            Res.drawable.idle_frame48,
            Res.drawable.idle_frame49,
            Res.drawable.idle_frame50,
            Res.drawable.idle_frame51,
            Res.drawable.idle_frame52,
            Res.drawable.idle_frame53,
            Res.drawable.idle_frame54,
            Res.drawable.idle_frame55,
            Res.drawable.idle_frame56,
            Res.drawable.idle_frame57,
            Res.drawable.idle_frame58,
            Res.drawable.idle_frame59,
            Res.drawable.idle_frame60,
            Res.drawable.idle_frame61,
            Res.drawable.idle_frame62,
            Res.drawable.idle_frame63,
            Res.drawable.idle_frame64,
            Res.drawable.idle_frame65,
            Res.drawable.idle_frame66,
            Res.drawable.idle_frame67,
            Res.drawable.idle_frame68,
            Res.drawable.idle_frame69,
            Res.drawable.idle_frame70,
            Res.drawable.idle_frame71,
            Res.drawable.idle_frame72,
            Res.drawable.idle_frame73,
            Res.drawable.idle_frame74,
            Res.drawable.idle_frame75,
            Res.drawable.idle_frame76,
            Res.drawable.idle_frame77,
            Res.drawable.idle_frame78,
            Res.drawable.idle_frame79,
            Res.drawable.idle_frame80,
            Res.drawable.idle_frame81,
            Res.drawable.idle_frame82,
            Res.drawable.idle_frame83,
            Res.drawable.idle_frame84,
            Res.drawable.idle_frame85,
            Res.drawable.idle_frame86,
            Res.drawable.idle_frame87,
            Res.drawable.idle_frame88,
            Res.drawable.idle_frame89,
            Res.drawable.idle_frame90,
            Res.drawable.idle_frame91,
            Res.drawable.idle_frame92,
            Res.drawable.idle_frame93,
            Res.drawable.idle_frame94,
            Res.drawable.idle_frame95,
            Res.drawable.idle_frame96,
            Res.drawable.idle_frame97,
            Res.drawable.idle_frame98,
            Res.drawable.idle_frame99,
            Res.drawable.idle_frame100,
            Res.drawable.idle_frame101,
            Res.drawable.idle_frame102,
            Res.drawable.idle_frame103,
            Res.drawable.idle_frame104,
            Res.drawable.idle_frame105,
            Res.drawable.idle_frame106,
            Res.drawable.idle_frame107,
            Res.drawable.idle_frame108,
            Res.drawable.idle_frame109,
            Res.drawable.idle_frame110,
            Res.drawable.idle_frame111,
            Res.drawable.idle_frame112,
            Res.drawable.idle_frame113,
            Res.drawable.idle_frame114,
            Res.drawable.idle_frame115,
            Res.drawable.idle_frame116,
            Res.drawable.idle_frame117,
            Res.drawable.idle_frame118,
            Res.drawable.idle_frame119,
            Res.drawable.idle_frame120,
            Res.drawable.idle_frame121,
            Res.drawable.idle_frame122,
            Res.drawable.idle_frame123,
            Res.drawable.idle_frame124,
            Res.drawable.idle_frame125,
            Res.drawable.idle_frame126,
            Res.drawable.idle_frame127,
            Res.drawable.idle_frame128,
            Res.drawable.idle_frame129,
            Res.drawable.idle_frame130,
            Res.drawable.idle_frame131,
            Res.drawable.idle_frame132,
            Res.drawable.idle_frame133,
            Res.drawable.idle_frame134,
            Res.drawable.idle_frame135,
            Res.drawable.idle_frame136,
            Res.drawable.idle_frame137,
            Res.drawable.idle_frame138,
            Res.drawable.idle_frame139,
            Res.drawable.idle_frame140,
            Res.drawable.idle_frame141,
            Res.drawable.idle_frame142,
            Res.drawable.idle_frame143,
            Res.drawable.idle_frame144,
            Res.drawable.idle_frame145,
            Res.drawable.idle_frame146,
            Res.drawable.idle_frame147,
            Res.drawable.idle_frame148,
            Res.drawable.idle_frame149,
            Res.drawable.idle_frame150,
            Res.drawable.idle_frame151,
            Res.drawable.idle_frame152,
            Res.drawable.idle_frame153,
            Res.drawable.idle_frame154,
            Res.drawable.idle_frame155,
            Res.drawable.idle_frame156,
            Res.drawable.idle_frame157,
            Res.drawable.idle_frame158,
            Res.drawable.idle_frame159,
            Res.drawable.idle_frame160,
            Res.drawable.idle_frame161,
            Res.drawable.idle_frame162,
            Res.drawable.idle_frame163,
            Res.drawable.idle_frame164,
            Res.drawable.idle_frame165,
            Res.drawable.idle_frame166,
            Res.drawable.idle_frame167,
            Res.drawable.idle_frame168,
            Res.drawable.idle_frame169,
            Res.drawable.idle_frame170,
            Res.drawable.idle_frame171,
            Res.drawable.idle_frame172,
            Res.drawable.idle_frame173,
            Res.drawable.idle_frame174,
            Res.drawable.idle_frame175,
            Res.drawable.idle_frame176,
            Res.drawable.idle_frame177,
            Res.drawable.idle_frame178,
            Res.drawable.idle_frame179,
            Res.drawable.idle_frame180,
            Res.drawable.idle_frame181,
            Res.drawable.idle_frame182,
            Res.drawable.idle_frame183,
            Res.drawable.idle_frame184,
            Res.drawable.idle_frame185,
            Res.drawable.idle_frame186,
            Res.drawable.idle_frame187,
            Res.drawable.idle_frame188,
            Res.drawable.idle_frame189,
            Res.drawable.idle_frame190,
            Res.drawable.idle_frame191,
            Res.drawable.idle_frame192
        )
    }
    val correctAnswersFrames = remember {
        listOf(
            Res.drawable.right_answer1,
            Res.drawable.right_answer2,
            Res.drawable.right_answer3,
            Res.drawable.right_answer4,
            Res.drawable.right_answer5,
            Res.drawable.right_answer6,
            Res.drawable.right_answer7,
            Res.drawable.right_answer8,
            Res.drawable.right_answer9,
            Res.drawable.right_answer10,
            Res.drawable.right_answer11,
            Res.drawable.right_answer12,
            Res.drawable.right_answer13,
            Res.drawable.right_answer14,
            Res.drawable.right_answer15,
            Res.drawable.right_answer16,
            Res.drawable.right_answer17,
            Res.drawable.right_answer18,
            Res.drawable.right_answer19,
            Res.drawable.right_answer20,
            Res.drawable.right_answer21,
            Res.drawable.right_answer22,
            Res.drawable.right_answer23,
            Res.drawable.right_answer24,
            Res.drawable.right_answer25,
            Res.drawable.right_answer26,
            Res.drawable.right_answer27,
            Res.drawable.right_answer28,
            Res.drawable.right_answer29,
            Res.drawable.right_answer30,
            Res.drawable.right_answer31,
            Res.drawable.right_answer32,
            Res.drawable.right_answer33,
            Res.drawable.right_answer34,
            Res.drawable.right_answer35,
            Res.drawable.right_answer36,
            Res.drawable.right_answer37,
            Res.drawable.right_answer38,
            Res.drawable.right_answer39,
            Res.drawable.right_answer40,
            Res.drawable.right_answer41,
            Res.drawable.right_answer42,
            Res.drawable.right_answer43,
            Res.drawable.right_answer44,
            Res.drawable.right_answer45,
            Res.drawable.right_answer46,
            Res.drawable.right_answer47,
            Res.drawable.right_answer48,
            Res.drawable.right_answer49,
            Res.drawable.right_answer50,
            Res.drawable.right_answer51,
            Res.drawable.right_answer52,
            Res.drawable.right_answer53,
            Res.drawable.right_answer54,
            Res.drawable.right_answer55,
            Res.drawable.right_answer56,
            Res.drawable.right_answer57,
            Res.drawable.right_answer58,
            Res.drawable.right_answer59,
            Res.drawable.right_answer60,
            Res.drawable.right_answer61,
            Res.drawable.right_answer62,
            Res.drawable.right_answer63,
            Res.drawable.right_answer64,
            Res.drawable.right_answer65,
            Res.drawable.right_answer66,
            Res.drawable.right_answer67,
            Res.drawable.right_answer68,
            Res.drawable.right_answer69,
            Res.drawable.right_answer70,
            Res.drawable.right_answer71,
            Res.drawable.right_answer72,
            Res.drawable.right_answer73,
            Res.drawable.right_answer74,
            Res.drawable.right_answer75,
            Res.drawable.right_answer76,
            Res.drawable.right_answer77,
            Res.drawable.right_answer78,
            Res.drawable.right_answer79,
            Res.drawable.right_answer80
        )
    }
    val sleepFrames = remember {
        listOf(
            Res.drawable.sleep_frame1, Res.drawable.sleep_frame2,
            Res.drawable.sleep_frame3,
            Res.drawable.sleep_frame4,
            Res.drawable.sleep_frame5,
            Res.drawable.sleep_frame6,
            Res.drawable.sleep_frame7,
            Res.drawable.sleep_frame8,
            Res.drawable.sleep_frame9,
            Res.drawable.sleep_frame10,
            Res.drawable.sleep_frame11,
            Res.drawable.sleep_frame12,
            Res.drawable.sleep_frame13,
            Res.drawable.sleep_frame14,
            Res.drawable.sleep_frame15,
            Res.drawable.sleep_frame16,
            Res.drawable.sleep_frame17,
            Res.drawable.sleep_frame18,
            Res.drawable.sleep_frame19,
            Res.drawable.sleep_frame20,
            Res.drawable.sleep_frame21,
            Res.drawable.sleep_frame22,
            Res.drawable.sleep_frame23,
            Res.drawable.sleep_frame24,
            Res.drawable.sleep_frame25,
            Res.drawable.sleep_frame26,
            Res.drawable.sleep_frame27,
            Res.drawable.sleep_frame28,
            Res.drawable.sleep_frame29,
            Res.drawable.sleep_frame30,
            Res.drawable.sleep_frame31,
            Res.drawable.sleep_frame32,
            Res.drawable.sleep_frame33,
            Res.drawable.sleep_frame34,
            Res.drawable.sleep_frame35,
            Res.drawable.sleep_frame36,
            Res.drawable.sleep_frame37,
            Res.drawable.sleep_frame38,
            Res.drawable.sleep_frame39,
            Res.drawable.sleep_frame40,
            Res.drawable.sleep_frame41,
            Res.drawable.sleep_frame42,
            Res.drawable.sleep_frame43,
            Res.drawable.sleep_frame44,
            Res.drawable.sleep_frame45,
            Res.drawable.sleep_frame46,
            Res.drawable.sleep_frame47,
            Res.drawable.sleep_frame48,
            Res.drawable.sleep_frame49,
            Res.drawable.sleep_frame50,
            Res.drawable.sleep_frame51,
            Res.drawable.sleep_frame52,
            Res.drawable.sleep_frame53,
            Res.drawable.sleep_frame54,
            Res.drawable.sleep_frame55,
            Res.drawable.sleep_frame56,
            Res.drawable.sleep_frame57,
            Res.drawable.sleep_frame58,
            Res.drawable.sleep_frame59,
            Res.drawable.sleep_frame60,
            Res.drawable.sleep_frame61,
            Res.drawable.sleep_frame62,
            Res.drawable.sleep_frame63,
            Res.drawable.sleep_frame64,
            Res.drawable.sleep_frame65,
            Res.drawable.sleep_frame66,
            Res.drawable.sleep_frame67,
            Res.drawable.sleep_frame68,
            Res.drawable.sleep_frame69,
            Res.drawable.sleep_frame70,
            Res.drawable.sleep_frame71,
            Res.drawable.sleep_frame72,
            Res.drawable.sleep_frame73,
            Res.drawable.sleep_frame74,
            Res.drawable.sleep_frame75,
            Res.drawable.sleep_frame76,
            Res.drawable.sleep_frame77,
            Res.drawable.sleep_frame78,
            Res.drawable.sleep_frame79,
            Res.drawable.sleep_frame80,
            Res.drawable.sleep_frame81,
            Res.drawable.sleep_frame82,
            Res.drawable.sleep_frame83,
            Res.drawable.sleep_frame84,
            Res.drawable.sleep_frame85,
            Res.drawable.sleep_frame86,
            Res.drawable.sleep_frame87,
            Res.drawable.sleep_frame88,
            Res.drawable.sleep_frame89,
            Res.drawable.sleep_frame90,
            Res.drawable.sleep_frame91,
            Res.drawable.sleep_frame92,
            Res.drawable.sleep_frame93,
            Res.drawable.sleep_frame94,
            Res.drawable.sleep_frame95,
            Res.drawable.sleep_frame96,
            Res.drawable.sleep_frame97,
            Res.drawable.sleep_frame98,
            Res.drawable.sleep_frame99,
            Res.drawable.sleep_frame100,
            Res.drawable.sleep_frame101,
            Res.drawable.sleep_frame102,
            Res.drawable.sleep_frame103,
            Res.drawable.sleep_frame104,
            Res.drawable.sleep_frame105,
            Res.drawable.sleep_frame106,
            Res.drawable.sleep_frame107,
            Res.drawable.sleep_frame108,
            Res.drawable.sleep_frame109,
            Res.drawable.sleep_frame110,
            Res.drawable.sleep_frame111,
            Res.drawable.sleep_frame112,
            Res.drawable.sleep_frame113,
            Res.drawable.sleep_frame114,
            Res.drawable.sleep_frame115,
            Res.drawable.sleep_frame116,
            Res.drawable.sleep_frame117,
            Res.drawable.sleep_frame118,
            Res.drawable.sleep_frame119,
            Res.drawable.sleep_frame120,
            Res.drawable.sleep_frame121,
            Res.drawable.sleep_frame122,
            Res.drawable.sleep_frame123,
            Res.drawable.sleep_frame124,
            Res.drawable.sleep_frame125,
            Res.drawable.sleep_frame126,
            Res.drawable.sleep_frame127,
            Res.drawable.sleep_frame128,
            Res.drawable.sleep_frame129,
            Res.drawable.sleep_frame130,
            Res.drawable.sleep_frame131,
            Res.drawable.sleep_frame132,
            Res.drawable.sleep_frame133,
            Res.drawable.sleep_frame134,
            Res.drawable.sleep_frame135,
            Res.drawable.sleep_frame136,
            Res.drawable.sleep_frame137,
            Res.drawable.sleep_frame138,
            Res.drawable.sleep_frame139,
            Res.drawable.sleep_frame140,
            Res.drawable.sleep_frame141,
            Res.drawable.sleep_frame142,
            Res.drawable.sleep_frame143,
            Res.drawable.sleep_frame144,
            Res.drawable.sleep_frame145,
            Res.drawable.sleep_frame146,
            Res.drawable.sleep_frame147,
            Res.drawable.sleep_frame148,
            Res.drawable.sleep_frame149,
            Res.drawable.sleep_frame150,
            Res.drawable.sleep_frame151,
            Res.drawable.sleep_frame152,
            Res.drawable.sleep_frame153,
            Res.drawable.sleep_frame154,
            Res.drawable.sleep_frame155,
            Res.drawable.sleep_frame156,
            Res.drawable.sleep_frame157,
            Res.drawable.sleep_frame158,
            Res.drawable.sleep_frame159,
            Res.drawable.sleep_frame160,
            Res.drawable.sleep_frame161,
            Res.drawable.sleep_frame162,
            Res.drawable.sleep_frame163,
            Res.drawable.sleep_frame164,
            Res.drawable.sleep_frame165,
            Res.drawable.sleep_frame166,
            Res.drawable.sleep_frame167,
            Res.drawable.sleep_frame168,
            Res.drawable.sleep_frame169,
            Res.drawable.sleep_frame170,
            Res.drawable.sleep_frame171,
            Res.drawable.sleep_frame172,
            Res.drawable.sleep_frame173,
            Res.drawable.sleep_frame174,
            Res.drawable.sleep_frame175,
            Res.drawable.sleep_frame176,
            Res.drawable.sleep_frame177,
            Res.drawable.sleep_frame178,
            Res.drawable.sleep_frame179,
            Res.drawable.sleep_frame180,
            Res.drawable.sleep_frame181,
            Res.drawable.sleep_frame182,
            Res.drawable.sleep_frame183,
            Res.drawable.sleep_frame184,
            Res.drawable.sleep_frame185,
            Res.drawable.sleep_frame186,
            Res.drawable.sleep_frame187,
            Res.drawable.sleep_frame188,
            Res.drawable.sleep_frame189,
            Res.drawable.sleep_frame190,
            Res.drawable.sleep_frame191,
            Res.drawable.sleep_frame192,
            Res.drawable.sleep_frame193,
            Res.drawable.sleep_frame194,
            Res.drawable.sleep_frame195,
            Res.drawable.sleep_frame196,
            Res.drawable.sleep_frame197,
            Res.drawable.sleep_frame198,
            Res.drawable.sleep_frame199,
            Res.drawable.sleep_frame200,
            Res.drawable.sleep_frame201,
            Res.drawable.sleep_frame202,
            Res.drawable.sleep_frame203,
            Res.drawable.sleep_frame204,
            Res.drawable.sleep_frame205,
            Res.drawable.sleep_frame206,
            Res.drawable.sleep_frame207,
            Res.drawable.sleep_frame208,
            Res.drawable.sleep_frame209,
            Res.drawable.sleep_frame210,
            Res.drawable.sleep_frame211,
            Res.drawable.sleep_frame212,
            Res.drawable.sleep_frame213,
            Res.drawable.sleep_frame214
        )
    }

    val paintersSleep = sleepFrames.map { painterResource(it) }
    val paintersCorrect = correctAnswersFrames.map { painterResource(it) }
    val paintersIdle = idleFrames.map { painterResource(it) }
    var painters = paintersIdle

    LaunchedEffect(feel) {
        when (feel) {
            FalehFeel.IDLE -> {
                duration.intValue = IDLE_TIME
                frames.intValue = IDLE
                frameName.value = IDLE_NAME
                painters = paintersIdle
            }

            FalehFeel.SLEEP -> {
                duration.intValue = SLEEP_TIME
                frames.intValue = SLEEP
                frameName.value = SLEEP_NAME
                painters = paintersSleep
            }

            FalehFeel.CORRECT -> {
                duration.intValue = CORRECT_TIME
                frames.intValue = CORRECT
                frameName.value = CORRECT_NAME
                delay(2500)
                duration.intValue = IDLE_TIME
                frames.intValue = IDLE
                frameName.value = IDLE_NAME
                painters = paintersCorrect
            }
        }
    }


    FeelsAnimationWidget(
        targetFrames = frames.intValue,
        modifier,
        duration.intValue,
        painters
    )
}

@Composable
private fun FeelsAnimationWidget(
    targetFrames: Int,
    modifier: Modifier,
    duration: Int,
    painters: List<Painter>
) {


    val value by rememberInfiniteTransition(label = "").animateValue(
        initialValue = 1,
        targetValue = targetFrames,
        typeConverter = Int.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = duration, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "frameIndex"
    )


    if (painters.isNotEmpty()) {
        Image(
            painter = painters[value],
            contentDescription = null,
            modifier = modifier
        )
    }
}

const val IDLE = 191
const val SLEEP = 214
const val CORRECT = 80

const val IDLE_NAME = "idle_frame"
const val SLEEP_NAME = "sleep_frame"
const val CORRECT_NAME = "right_answer"

const val IDLE_TIME = 7500
const val SLEEP_TIME = 9000
const val CORRECT_TIME = 2700