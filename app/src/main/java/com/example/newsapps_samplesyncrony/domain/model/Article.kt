package com.example.newsapps_samplesyncrony.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val author: String, val content: String, val description: String, val urlToImage: String
)
