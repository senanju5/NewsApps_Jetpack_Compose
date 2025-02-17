package com.example.newsapps_samplesyncrony.domain.repository

import com.example.newsapps_samplesyncrony.data.model.News
import com.example.newsapps_samplesyncrony.utils.ResponseWrapper
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNews(): Flow<ResponseWrapper<News>>
}