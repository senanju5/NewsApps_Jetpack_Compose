package com.example.newsapps_samplesyncrony.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.newsapps_samplesyncrony.domain.model.Article
import com.example.newsapps_samplesyncrony.presentation.components.ImageViewWithCoil
import com.example.newsapps_samplesyncrony.presentation.navigation.AppNavHost
import com.example.newsapps_samplesyncrony.presentation.navigation.Screen
import com.example.newsapps_samplesyncrony.presentation.theme.NewsApps_SampleSyncronyTheme
import com.example.newsapps_samplesyncrony.presentation.theme.Purple40
import com.example.newsapps_samplesyncrony.presentation.uistate.NewsUiState
import com.example.newsapps_samplesyncrony.presentation.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsApps_SampleSyncronyTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { AppBar() }) { paddingValues ->
                    Column(modifier = Modifier.padding(paddingValues)) { AppNavHost() }

                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar() {
    TopAppBar(
        title = { Text(text = "News App Demo") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Purple40
        ),
    )
}


@Composable
fun NewsDetails(newsViewModel: NewsViewModel ) {
    val selectedArticle = newsViewModel.selectedArticle.collectAsState()
    val article = selectedArticle.value
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(0.dp, 10.dp, 0.dp, 0.dp)) {
        article?.description?.let { Text(text = it) }

    }
}

@Composable
fun NewsListView(newsViewModel: NewsViewModel,articles: List<Article>, onNavigateToArticle: (String) -> Unit) {
   LazyColumn {
       items(articles){
           article->
           NewListItem(newsViewModel,article = article, onNavigateToArticle )
       }
   }
}


@Composable
fun NewsList(
    newsViewModel: NewsViewModel,
    onNavigateToArticle: (String) -> Unit
) {
    val context = LocalContext.current
    val uiState = newsViewModel.newsUiState.collectAsState()

    LaunchedEffect(Unit) {
        Log.d("side Effect","LaunchedEffect")
        newsViewModel.getNews()
    }

    val count = remember { mutableStateOf(0) }

    SideEffect {
        // This will run after the composable is recomposed
        Log.d("side Effect", "Current count is: ${count.value}")
    }

    when (uiState.value) {
        is NewsUiState.Success -> {
            NewsListView(newsViewModel, (uiState.value as NewsUiState.Success).news.articles, onNavigateToArticle)
        }

        is NewsUiState.Error -> {
            Toast.makeText(context, (uiState.value as NewsUiState.Error).message, Toast.LENGTH_LONG)
                .show()
        }

        is NewsUiState.Loading -> {
            ProgressBar()
        }
    }
}


@Composable
fun NewListItem(newsViewModel:NewsViewModel,article: Article, onNavigateToArticle: (String)-> Unit ) {

    Column(modifier = Modifier.padding(0.dp, 5.dp, 0.dp, 5.dp)) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp, 1.dp, 8.dp, 1.dp),
            onClick = {
                newsViewModel.setSelectedArticle(article)
                onNavigateToArticle(Screen.NewsDetails.route)
            }
        ) {
            Row {
                ImageViewWithCoil(article.urlToImage)
                Text(
                    text = article.description,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(8.dp, 0.dp, 8.dp, 0.dp)
                        .align(Alignment.CenterVertically),
                )
            }
        }

    }
}

@Composable
fun ProgressBar() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}


fun printText(){
    printInline()
}

 fun printInline(){
    println("printInline")
}
