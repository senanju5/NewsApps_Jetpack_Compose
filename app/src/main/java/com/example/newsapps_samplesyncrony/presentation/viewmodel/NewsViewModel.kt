package com.example.newsapps_samplesyncrony.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapps_samplesyncrony.domain.model.Article
import com.example.newsapps_samplesyncrony.domain.usecase.NewsUseCase
import com.example.newsapps_samplesyncrony.presentation.uistate.NewsUiState
import com.example.newsapps_samplesyncrony.utils.ResponseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsUseCase: NewsUseCase):ViewModel() {

    private val _newsUiState by lazy { MutableStateFlow<NewsUiState>(NewsUiState.Loading)  }
    internal val newsUiState: StateFlow<NewsUiState> = _newsUiState

    private val _selectedArticle = MutableStateFlow<Article?>(null)
    internal val selectedArticle: StateFlow<Article?> = _selectedArticle

    fun setSelectedArticle(article: Article) {
        _selectedArticle.value = article
    }
    fun getNews(){
        viewModelScope.launch {
            newsUseCase.getNews().collect{
                if (_newsUiState.value == NewsUiState.Loading) {
                    newsUseCase.getNews().collect{
                        when(it){
                            is ResponseWrapper.Error -> {
                                _newsUiState.value = NewsUiState.Error(it.message)
                            }
                            is ResponseWrapper.Loading -> {
                                _newsUiState.value = NewsUiState.Loading
                            }
                            is ResponseWrapper.Success -> {
                                _newsUiState.value = NewsUiState.Success(it.data)
                            }
                        }
                    }
                }
            }
        }

    }

}