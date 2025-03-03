package com.gonzaloracergalan.portfolio.data.dt.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InteresDTO(
    @SerialName("nombre")
    val nombre: String? = null,
    @SerialName("palabrasClave")
    val palabrasClave: List<String>? = null
)