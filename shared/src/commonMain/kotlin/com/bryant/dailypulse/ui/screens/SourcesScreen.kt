package com.bryant.dailypulse.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.bryant.dailypulse.android.ui.components.ErrorMessage
import com.bryant.dailypulse.android.ui.components.Loader
import com.bryant.dailypulse.android.ui.components.Toolbar
import com.bryant.dailypulse.sources.application.Source
import com.bryant.dailypulse.sources.presentation.SourcesViewModel
import com.bryant.dailypulse.ui.components.PullToRefresh
import org.koin.compose.koinInject

class SourcesScreen : Screen {
    @Composable
    override fun Content() {
        SourcesScreenContent()
    }
}

@Composable
fun SourcesScreenContent(
    sourcesViewModel: SourcesViewModel = koinInject(),
) {
    val sourcesState = sourcesViewModel.sourcesState.collectAsState()
    val navigator = LocalNavigator.currentOrThrow

    Column {
        Toolbar(
            text = { Text("Sources") },
            onUpButtonClick = { navigator.pop() },
        )
        if (sourcesState.value.loading)
            Loader()
        if (sourcesState.value.error != null)
            ErrorMessage(sourcesState.value.error!!)
        if (sourcesState.value.sources.isNotEmpty())
            SourcesListView(viewModel = sourcesViewModel)
    }
}

@Composable
fun SourcesListView(viewModel: SourcesViewModel) {
    PullToRefresh(
        refreshing = viewModel.sourcesState.value.loading,
        onRefresh = { viewModel.getSources(true) },
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(
                items = viewModel.sourcesState.value.sources,
                key = { source -> source.id }
            ) {
                SourceItem(it)
            }
        }
    }
}

@Composable
fun SourceItem(source: Source) {
    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 20.dp)
    ) {
        Text(
            source.name,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        Text(
            source.desc,
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            source.origin,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.align(Alignment.End),
        )
    }
}