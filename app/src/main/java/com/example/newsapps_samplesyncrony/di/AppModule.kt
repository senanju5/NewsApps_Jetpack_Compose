package com.example.newsapps_samplesyncrony.di

import com.example.newsapps_samplesyncrony.data.network.NewsApiService
import com.example.newsapps_samplesyncrony.data.remotedata.RemoteDataSource
import com.example.newsapps_samplesyncrony.data.repository.NewsRepositoryImpl
import com.example.newsapps_samplesyncrony.domain.repository.NewsRepository
import com.example.newsapps_samplesyncrony.utils.ApiConfig.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().
        readTimeout(15, TimeUnit.SECONDS).
        connectTimeout(15, TimeUnit.SECONDS).build()
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun provideRetrofitInstance(json: Json, okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL).
            client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsApiService(retrofit: Retrofit): NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideNewRepository(
        remoteDataSource: RemoteDataSource
    ): NewsRepository {
        return NewsRepositoryImpl(remoteDataSource)
    }
}