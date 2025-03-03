package com.gonzaloracergalan.portfolio.data.dt.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CertificadoDTO(
    @SerialName("emisor")
    val emisor: String? = null,
    @SerialName("fecha")
    val fecha: String? = null,
    @SerialName("nombre")
    val nombre: String? = null,
    @SerialName("url")
    val url: String? = null
)