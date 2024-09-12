package com.bryant.dailypulse.android.di

import app.cash.sqldelight.db.SqlDriver
import com.bryant.dailypulse.db.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import bryant.dailypulse.db.DailyPulseDatabase

val databaseModule = module {
    single<SqlDriver> { DatabaseDriverFactory(androidContext()).createDriver()!! }

    single<DailyPulseDatabase> { DailyPulseDatabase(get()) }
}