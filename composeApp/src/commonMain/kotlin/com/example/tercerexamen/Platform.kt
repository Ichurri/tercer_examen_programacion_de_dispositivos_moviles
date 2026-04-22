package com.example.tercerexamen

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform