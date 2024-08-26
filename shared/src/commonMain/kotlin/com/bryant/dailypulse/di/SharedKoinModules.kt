package com.bryant.dailypulse.di

import com.bryant.dailypulse.articles.di.articlesModule

val sharedKoinModules = listOf(
    articlesModule,
    networkModule,
)