package com.bryant.dailypulse.di

import app.cash.sqldelight.db.SqlDriver
import bryant.dailypulse.db.DailyPulseDatabase
import com.bryant.dailypulse.db.DatabaseDriverFactory
import org.koin.dsl.module

val databaseModule = module {
    single<SqlDriver> { DatabaseDriverFactory().createDriver()!! }
    single<DailyPulseDatabase> { DailyPulseDatabase(get()) }
}