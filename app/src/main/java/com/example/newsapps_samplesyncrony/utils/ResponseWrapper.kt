package com.example.newsapps_samplesyncrony.utils

sealed class ResponseWrapper<T> {
    data class Success<T>(val data:T): ResponseWrapper<T>()
    data class Error <T> (val message:String, val exception:Throwable?=null): ResponseWrapper<T>()
    class Loading<T>:ResponseWrapper<T>()
}