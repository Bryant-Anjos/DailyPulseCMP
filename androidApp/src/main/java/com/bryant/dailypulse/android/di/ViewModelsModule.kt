package com.bryant.dailypulse.android.di

import com.bryant.dailypulse.articles.presentation.ArticlesViewModel
import com.bryant.dailypulse.sources.presentation.SourcesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { ArticlesViewModel(get()) }
    viewModel { SourcesViewModel(get()) }
}