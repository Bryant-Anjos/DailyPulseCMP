package com.bryant.dailypulse.sources.data

import bryant.dailypulse.db.DailyPulseDatabase

class SourcesDataSource(private val database: DailyPulseDatabase?) {
    fun getAllSources(): List<SourceRaw> =
        database
            ?.dailyPulseDatabaseQueries
            ?.selectAllSources(::mapToSourceRaw)
            ?.executeAsList()
            ?: listOf()

    fun insertSources(sources: List<SourceRaw>) {
        database?.dailyPulseDatabaseQueries?.transaction {
            sources.forEach { insertSource(it) }
        }
    }

    fun clearSources() = database?.dailyPulseDatabaseQueries?.removeAllSources()

    private fun insertSource(sourceRaw: SourceRaw) {
        database?.dailyPulseDatabaseQueries?.insertSource(
            sourceRaw.id,
            sourceRaw.name,
            sourceRaw.desc,
            sourceRaw.language,
            sourceRaw.country,
        )
    }

    private fun mapToSourceRaw(
        id: String,
        name: String,
        desc: String,
        language: String,
        country: String
    ): SourceRaw = SourceRaw(id, name, desc, language, country)
}