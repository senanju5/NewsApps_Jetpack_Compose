package com.example.newsapps_samplesyncrony.data.repository
import com.example.newsapps_samplesyncrony.data.model.News
import com.example.newsapps_samplesyncrony.data.network.safeApiCall
import com.example.newsapps_samplesyncrony.data.remotedata.RemoteDataSource
import com.example.newsapps_samplesyncrony.domain.repository.NewsRepository
import com.example.newsapps_samplesyncrony.utils.ResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsRepositoryImpl@Inject constructor(private val remoteDataSource: RemoteDataSource): NewsRepository {
    override suspend fun getNews(): Flow<ResponseWrapper<News>> = flow {
        emit(ResponseWrapper.Loading())
        val result = safeApiCall {remoteDataSource.getNews()}
        emit(result)
    }.flowOn(Dispatchers.IO)
}