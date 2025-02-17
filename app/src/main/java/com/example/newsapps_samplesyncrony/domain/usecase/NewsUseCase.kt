package com.example.newsapps_samplesyncrony.domain.usecase

import com.example.newsapps_samplesyncrony.data.model.News
import com.example.newsapps_samplesyncrony.domain.model.Article
import com.example.newsapps_samplesyncrony.domain.model.DomainNews
import com.example.newsapps_samplesyncrony.domain.repository.NewsRepository
import com.example.newsapps_samplesyncrony.utils.ResponseWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsUseCase @Inject constructor(private val newsRepository: NewsRepository)   {
    private fun getDomainNews(flowNews: ResponseWrapper<News>): ResponseWrapper<DomainNews> {
        when (flowNews) {
            is ResponseWrapper.Error -> {
                return ResponseWrapper.Error(flowNews.message, flowNews.exception)
            }
            is ResponseWrapper.Loading -> {
                return ResponseWrapper.Loading()
            }
            is ResponseWrapper.Success -> {
                val articles = flowNews.data.articles.map {
                    Article(
                        author = it.author.toString(),
                        content = it.content.toString(),
                        description = it.description.toString(),
                        urlToImage = it.urlToImage.toString()
                    )
                }
                return ResponseWrapper.Success(DomainNews(articles))
            }
        }
    }

    suspend fun getNews(): Flow<ResponseWrapper<DomainNews>> {
        return newsRepository.getNews().map {
            getDomainNews(it)
        }
    }
}