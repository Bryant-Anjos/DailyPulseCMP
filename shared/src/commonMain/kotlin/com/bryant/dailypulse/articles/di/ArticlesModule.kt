package com.bryant.dailypulse.articles.di

import com.bryant.dailypulse.articles.data.ArticlesDataSource
import com.bryant.dailypulse.articles.data.ArticlesRepository
import com.bryant.dailypulse.articles.data.ArticlesService
import com.bryant.dailypulse.articles.application.ArticlesUseCase
import com.bryant.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {
    single<ArticlesService> { ArticlesService(get()) }
    single<ArticlesUseCase> { ArticlesUseCase(get()) }
    single<ArticlesViewModel> { ArticlesViewModel(get()) }
    single<ArticlesDataSource> { ArticlesDataSource(get()) }
    single<ArticlesRepository> { ArticlesRepository(get(), get()) }
}