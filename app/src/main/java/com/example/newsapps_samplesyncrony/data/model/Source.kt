package com.example.newsapps_samplesyncrony.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Source(
    @SerialName("id")
    val id: String?, // time
    @SerialName("name")
    val name: String? // Bolsamania.com
)