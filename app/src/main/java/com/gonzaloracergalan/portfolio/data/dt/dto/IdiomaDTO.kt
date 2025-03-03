package com.gonzaloracergalan.portfolio.data.dt.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IdiomaDTO(
    @SerialName("fluidez")
    val fluidez: String? = null,
    @SerialName("idioma")
    val idioma: String? = null
)