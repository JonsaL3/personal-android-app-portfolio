package com.gonzaloracergalan.portfolio.data.dt.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PublicacionDTO(
    @SerialName("editor")
    val editor: String? = null,
    @SerialName("fechaPublicacion")
    val fechaPublicacion: String? = null,
    @SerialName("nombre")
    val nombre: String? = null,
    @SerialName("resumen")
    val resumen: String? = null,
    @SerialName("url")
    val url: String? = null
)