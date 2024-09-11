package com.bryant.dailypulse.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.bryant.dailypulse.Platform
import com.bryant.dailypulse.android.ui.components.Toolbar

class AboutScreen : Screen {
    @Composable
    override fun Content() {
        AboutScreenContent()
    }
}

@Composable
fun AboutScreenContent() {
    val navigator = LocalNavigator.currentOrThrow

    Column {
        Toolbar(
            text = { Text("About Device") },
            onUpButtonClick = { navigator.pop() },
        )
        ContentView()
    }
}

@Composable
private fun ContentView() {
    val items = makeItems()
    
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(items) { row ->
            RowView(title = row.first, subtitle = row.second)
        }
    }
}

private fun makeItems(): List<Pair<String, String>> {
    val platform = Platform()
    platform.logSystemInfo()

    return listOf(
        Pair("Operating System", "${platform.osName} ${platform.osVersion}"),
        Pair("Device", platform.deviceModel),
        Pair("Density", platform.density.toString()),
    )
}

@Composable
fun RowView(
    title: String,
    subtitle: String,
) {
    Column(
        modifier = Modifier.padding(8.dp),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
    Divider()
}