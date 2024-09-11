package com.bryant.dailypulse.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.bryant.dailypulse.android.ui.components.ErrorMessage
import com.bryant.dailypulse.articles.application.Article
import com.bryant.dailypulse.articles.presentation.ArticlesViewModel
import com.bryant.dailypulse.ui.components.PullToRefresh
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.ktor.http.Url
import org.koin.compose.koinInject

class ArticlesScreen : Screen {
    @Composable
    override fun Content() {
        ArticlesScreenContent()
    }
}

@Composable
fun ArticlesScreenContent(
    articlesViewModel: ArticlesViewModel = koinInject()
) {
    val articlesState = articlesViewModel.articlesState.collectAsState()

    Column {
        AppBar()

        if (articlesState.value.error != null)
            ErrorMessage(articlesState.value.error!!)
        if (articlesState.value.articles.isNotEmpty())
            ArticlesListView(articlesViewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar() {
    val navigator = LocalNavigator.currentOrThrow

    TopAppBar(
        title = {
            Text(text = "Articles")
        },
        actions = {
            IconButton(onClick = {
                navigator.push(SourcesScreen())
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.List,
                    contentDescription = "Articles Source Button",
                )
            }
            IconButton(onClick = {
                navigator.push(AboutScreen())
            }) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "About Device Button",
                )
            }
        }
    )
}

@Composable
fun ArticlesListView(viewModel: ArticlesViewModel) {
    PullToRefresh(
        refreshing = viewModel.articlesState.value.loading,
        onRefresh = { viewModel.getArticles(true) },
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(viewModel.articlesState.value.articles) { article ->
                ArticleItemView(article = article)
            }
        }
    }
}

@Composable
fun ArticleItemView(article: Article) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        KamelImage(
            resource = asyncPainterResource(data = Url(article.imageUrl)),
            contentDescription = "article image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(200.dp),
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.title,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 12.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = article.desc)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.date,
            style = TextStyle(color = Color.Gray),
            modifier = Modifier.align(Alignment.End),
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}
