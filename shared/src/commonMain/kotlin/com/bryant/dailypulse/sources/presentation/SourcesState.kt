package com.bryant.dailypulse.sources.presentation

import com.bryant.dailypulse.sources.application.Source

data class SourcesState(
    val sources: List<Source> = listOf(),
    val loading: Boolean = false,
    val error: String? = null,
)