package com.gonzaloracergalan.portfolio.data.dt.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EducacionDTO(
    @SerialName("area")
    val area: String? = null,
    @SerialName("calificacion")
    val calificacion: String? = null,
    @SerialName("cursos")
    val cursos: List<String>? = null,
    @SerialName("fechaFin")
    val fechaFin: String? = null,
    @SerialName("fechaInicio")
    val fechaInicio: String? = null,
    @SerialName("institucion")
    val institucion: String? = null,
    @SerialName("tipoEstudio")
    val tipoEstudio: String? = null,
    @SerialName("url")
    val url: String? = null
)