package com.bryant.dailypulse.di

import app.cash.sqldelight.db.SqlDriver
import com.bryant.dailypulse.db.DatabaseDriverFactory
import org.koin.dsl.module
import bryant.dailypulse.db.DailyPulseDatabase

val databaseModule = module {
    single<SqlDriver> { DatabaseDriverFactory().createDriver()!! }

    single<DailyPulseDatabase> { DailyPulseDatabase(get()) }
}