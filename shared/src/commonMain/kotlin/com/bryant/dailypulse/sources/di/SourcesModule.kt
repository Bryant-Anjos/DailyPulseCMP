package com.bryant.dailypulse.sources.di

import com.bryant.dailypulse.sources.data.SourcesDataSource
import com.bryant.dailypulse.sources.data.SourcesRepository
import com.bryant.dailypulse.sources.data.SourcesService
import com.bryant.dailypulse.sources.application.SourcesUseCase
import com.bryant.dailypulse.sources.presentation.SourcesViewModel
import org.koin.dsl.module

val sourcesModule = module {
    single<SourcesService> { SourcesService(get()) }
    single<SourcesUseCase> { SourcesUseCase(get()) }
    single<SourcesViewModel> { SourcesViewModel(get()) }
    single<SourcesDataSource> { SourcesDataSource(getOrNull()) }
    single<SourcesRepository> { SourcesRepository(get(), get()) }
}