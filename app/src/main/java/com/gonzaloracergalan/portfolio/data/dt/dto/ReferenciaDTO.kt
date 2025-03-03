package com.gonzaloracergalan.portfolio.data.dt.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReferenciaDTO(
    @SerialName("nombre")
    val nombre: String? = null,
    @SerialName("referencia")
    val referencia: String? = null
)