package com.gonzaloracergalan.portfolio.data.dt.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BasicoDTO(
    @SerialName("correo")
    val correo: String? = null,
    @SerialName("etiqueta")
    val etiqueta: String? = null,
    @SerialName("imagen")
    val imagen: String? = null,
    @SerialName("nombre")
    val nombre: String? = null,
    @SerialName("perfiles")
    val perfiles: List<PerfilDTO>? = null,
    @SerialName("resumen")
    val resumen: String? = null,
    @SerialName("telefono")
    val telefono: String? = null,
    @SerialName("ubicacion")
    val ubicacion: UbicacionDTO? = null,
    @SerialName("url")
    val url: String? = null
)