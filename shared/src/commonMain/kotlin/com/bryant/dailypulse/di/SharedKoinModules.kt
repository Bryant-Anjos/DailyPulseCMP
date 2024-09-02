package com.bryant.dailypulse.di

import com.bryant.dailypulse.articles.di.articlesModule
import com.bryant.dailypulse.sources.di.sourcesModule

val sharedKoinModules = listOf(
    articlesModule,
    sourcesModule,
    networkModule,
)