package com.example.newsapps_samplesyncrony.data.remotedata

import com.example.newsapps_samplesyncrony.data.model.News
import com.example.newsapps_samplesyncrony.data.network.NewsApiService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val newsApiService: NewsApiService) {
    suspend fun getNews():News{
       return newsApiService.getNews()
    }
}