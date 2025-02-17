package com.example.newsapps_samplesyncrony.data.network

import com.example.newsapps_samplesyncrony.data.model.News
import com.example.newsapps_samplesyncrony.utils.ApiConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET(ApiConfig.NEWS_END_POINT)
    suspend fun getNews(@Query("q") q:String = ApiConfig.Q,
                        @Query("from") fromDate:String = ApiConfig.FROM_DATE,
                        @Query("sortBy") sortBy:String = ApiConfig.SORT_BY,
                        @Query("apiKey") apiKey:String = ApiConfig.API_KEY,
                        @Query("page") pageNumber:String = ApiConfig.PAGE_NUMBER,
    ): News
}