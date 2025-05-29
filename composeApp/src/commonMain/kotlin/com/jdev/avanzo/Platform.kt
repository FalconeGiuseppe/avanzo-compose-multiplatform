package com.jdev.avanzo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform