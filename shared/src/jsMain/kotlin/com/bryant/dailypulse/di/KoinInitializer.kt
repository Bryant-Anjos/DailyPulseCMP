package com.bryant.dailypulse.di

import org.koin.core.context.startKoin

fun initKoin() = startKoin {
    modules(
        sharedKoinModules
    )
}.koin
