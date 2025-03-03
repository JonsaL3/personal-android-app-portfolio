package com.gonzaloracergalan.portfolio.data.dt.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrabajoDTO(
    @SerialName("fechaFin")
    val fechaFin: String? = null,
    @SerialName("fechaInicio")
    val fechaInicio: String? = null,
    @SerialName("logros")
    val logros: List<String?>? = null,
    @SerialName("nombre")
    val nombre: String? = null,
    @SerialName("posicion")
    val posicion: String? = null,
    @SerialName("resumen")
    val resumen: String? = null,
    @SerialName("url")
    val url: String? = null
)