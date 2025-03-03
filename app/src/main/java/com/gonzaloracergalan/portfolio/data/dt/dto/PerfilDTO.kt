package com.gonzaloracergalan.portfolio.data.dt.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PerfilDTO(
    @SerialName("red")
    val red: String? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("usuario")
    val usuario: String? = null
)