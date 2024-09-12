package com.bryant.dailypulse.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import bryant.dailypulse.db.DailyPulseDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return JdbcSqliteDriver(
            url = "jdbc:sqlite:DailyPulseDatabase.db",
            schema = DailyPulseDatabase.Schema,
        )
    }
}