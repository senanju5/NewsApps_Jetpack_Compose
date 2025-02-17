package com.example.newsapps_samplesyncrony.presentation.navigation

sealed  class WrapperRespo<T> {
    data class Success<T>(val data:T):WrapperRespo<T>()
    data class Error<T>(val message:String):WrapperRespo<T>()
    class Loading<T>:WrapperRespo<T>()

}