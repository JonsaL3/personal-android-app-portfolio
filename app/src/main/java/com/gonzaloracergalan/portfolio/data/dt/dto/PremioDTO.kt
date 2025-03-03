package com.gonzaloracergalan.portfolio.data.dt.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PremioDTO(
    @SerialName("fecha")
    val fecha: String? = null,
    @SerialName("otorgadoPor")
    val otorgadoPor: String? = null,
    @SerialName("resumen")
    val resumen: String? = null,
    @SerialName("titulo")
    val titulo: String? = null
)