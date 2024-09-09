package com.bryant.dailypulse.sources.application

import com.bryant.dailypulse.sources.data.SourceRaw
import com.bryant.dailypulse.sources.data.SourcesRepository

class SourcesUseCase(
    private val repository: SourcesRepository,
) {
    suspend fun getSources(forceFetch: Boolean): List<Source> {
        val sourcesRaw = repository.getSources(forceFetch)
        return mapSources(sourcesRaw)
    }

    private fun mapSources(sourcesRaw: List<SourceRaw>): List<Source> = sourcesRaw.map { raw ->
        Source(
            raw.id,
            raw.name,
            raw.desc,
            "${raw.country} - ${raw.language}",
        )
    }
}