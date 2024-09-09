package com.bryant.dailypulse.android.screens

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
import com.bryant.dailypulse.android.ui.ErrorMessage
import com.bryant.dailypulse.android.ui.Loader
import com.bryant.dailypulse.android.ui.Toolbar
import com.bryant.dailypulse.sources.application.Source
import com.bryant.dailypulse.sources.presentation.SourcesViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import org.koin.androidx.compose.getViewModel

@Composable
fun SourcesScreen(
    onUpButtonClick: () -> Unit,
    sourcesViewModel: SourcesViewModel = getViewModel(),
) {
    val sourcesState = sourcesViewModel.sourcesState.collectAsState()

    Column {
        Toolbar(
            text = { Text("Sources") },
            onUpButtonClick = onUpButtonClick,
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
    SwipeRefresh(
        state = SwipeRefreshState(viewModel.sourcesState.value.loading),
        onRefresh = { viewModel.getSources(true) }
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