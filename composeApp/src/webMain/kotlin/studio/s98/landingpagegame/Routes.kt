package studio.s98.landingpagegame

import kotlinx.serialization.Serializable

@Serializable
object Title

@Serializable
object Board

@Serializable
data class Result(val starsCount: Int, val missingWords: List<String>)