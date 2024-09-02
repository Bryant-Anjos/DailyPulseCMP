package com.bryant.dailypulse.sources

class SourcesUseCase(
    private val service: SourcesService,
) {
    suspend fun getSources(): List<Source> {
        val sourcesRaw = service.fetchSources()
        return mapSources(sourcesRaw)
    }

    private fun mapSources(sourcesRaw: List<SourceRaw>): List<Source> = sourcesRaw.map { raw ->
        Source(
            raw.id,
            raw.name,
            raw.description,
            raw.url,
            raw.category,
            raw.language,
            raw.country,
        )
    }
}