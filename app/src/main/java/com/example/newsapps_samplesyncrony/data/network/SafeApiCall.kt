package com.example.newsapps_samplesyncrony.data.network

import com.example.newsapps_samplesyncrony.utils.ResponseWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

 suspend inline  fun <T> safeApiCall (coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO, crossinline apiCall: suspend () -> T): ResponseWrapper<T> {
    return withContext(coroutineDispatcher){
        try {
            ResponseWrapper.Success(apiCall.invoke())
        } catch (exception: Exception) {
            ResponseWrapper.Error(exception.message?: "Something went wrong", exception)
        }
    }
}