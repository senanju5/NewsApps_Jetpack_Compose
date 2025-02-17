package com.example.newsapps_samplesyncrony.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Article(
    @SerialName("author")
    val author: String?, // redaccion@bolsamania.com (César Nuez)
    @SerialName("content")
    val content: String?, // Nueva demostración de fuerza en Netflix que consigue perforar la resistencia de los 908 dólares.La compañía no puede ofrecer un mejor aspecto técnico ya que se mueve en subida libre, sin resistenci… [+733 chars]
    @SerialName("description")
    val description: String?, // Nueva demostración de fuerza en Netflix que consigue perforar la resistencia de los 908 dólares.
    @SerialName("publishedAt")
    val publishedAt: String?, // 2024-12-12T12:59:06Z
    @SerialName("source")
    val source: Source?,
    @SerialName("title")
    val title: String?, // Compañías que se deben tener en cuenta este jueves en Wall Street
    @SerialName("url")
    val url: String?, // https://www.bolsamania.com/noticias/analisis-tecnico/companias-que-se-deben-tener-en-cuenta-este-jueves-en-wall-street--18311420.html
    @SerialName("urlToImage")
    val urlToImage: String? // https://img6.s3wfg.com/web/img/images_uploaded/b/5/cbwallstreet3.jpg
)