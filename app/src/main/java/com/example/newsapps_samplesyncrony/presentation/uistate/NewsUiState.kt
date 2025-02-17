package com.example.newsapps_samplesyncrony.presentation.uistate

import com.example.newsapps_samplesyncrony.domain.model.DomainNews

sealed class NewsUiState {
    data class Success(val news: DomainNews): NewsUiState()
    data class Error  (val message:String): NewsUiState()
    object Loading : NewsUiState()
}