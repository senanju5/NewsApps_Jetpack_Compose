package com.example.newsapps_samplesyncrony.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class News(
    @SerialName("articles")
    val articles: List<Article>,
    @SerialName("status")
    val status: String?, // ok
    @SerialName("totalResults")
    val totalResults: Int? // 851
)