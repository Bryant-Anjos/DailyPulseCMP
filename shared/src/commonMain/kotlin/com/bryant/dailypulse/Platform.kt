package com.bryant.dailypulse

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform