package com.bryant.dailypulse

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.bryant.dailypulse.di.initKoin
import com.bryant.dailypulse.ui.App

val koin = initKoin()

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow("Daily Pulse") {
        App(koin)
    }
}