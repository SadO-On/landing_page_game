package studio.s98.landingpagegame

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform