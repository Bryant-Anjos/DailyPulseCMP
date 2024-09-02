package com.bryant.dailypulse.sources.di

import com.bryant.dailypulse.sources.SourcesService
import com.bryant.dailypulse.sources.SourcesUseCase
import com.bryant.dailypulse.sources.SourcesViewModel
import org.koin.dsl.module

val sourcesModule = module {
    single<SourcesService> { SourcesService(get()) }
    single<SourcesUseCase> { SourcesUseCase(get()) }
    single<SourcesViewModel> { SourcesViewModel(get()) }
}