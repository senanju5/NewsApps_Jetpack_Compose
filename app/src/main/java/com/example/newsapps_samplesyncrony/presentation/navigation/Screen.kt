package com.example.newsapps_samplesyncrony.presentation.navigation


sealed class Screen(val route:String) {
    object NewsList:Screen("newsList")
    object NewsDetails:Screen("newsDetails")
}