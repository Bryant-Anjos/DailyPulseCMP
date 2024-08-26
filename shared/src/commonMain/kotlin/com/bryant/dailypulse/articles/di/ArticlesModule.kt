package com.bryant.dailypulse.articles.di

import com.bryant.dailypulse.articles.ArticlesService
import com.bryant.dailypulse.articles.ArticlesUseCase
import com.bryant.dailypulse.articles.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {
    single<ArticlesService> { ArticlesService(get()) }
    single<ArticlesUseCase> { ArticlesUseCase(get()) }
    single<ArticlesViewModel> { ArticlesViewModel(get()) }
}