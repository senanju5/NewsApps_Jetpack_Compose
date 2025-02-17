package com.example.newsapps_samplesyncrony.presentation.navigation

import com.example.newsapps_samplesyncrony.data.model.News

sealed class UiState{
    data class Success(val data:List<News>):UiState()
    data class Error(val message:String):UiState()
    object Loading:UiState()

}