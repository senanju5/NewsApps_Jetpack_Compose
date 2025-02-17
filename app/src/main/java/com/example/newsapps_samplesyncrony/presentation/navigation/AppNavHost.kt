package com.example.newsapps_samplesyncrony.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapps_samplesyncrony.presentation.ui.NewsDetails
import com.example.newsapps_samplesyncrony.presentation.ui.NewsList
import com.example.newsapps_samplesyncrony.presentation.viewmodel.NewsViewModel

@Composable
fun AppNavHost(sharedViewModel: NewsViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.NewsList.route) {
        composable(route = Screen.NewsList.route) {
            NewsList(sharedViewModel, onNavigateToArticle = { route ->
                navController.navigate(route)
            })
        }
        composable(
            route = Screen.NewsDetails.route
        ) {
            NewsDetails(sharedViewModel)

        }


    }
}