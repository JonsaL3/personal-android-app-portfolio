package com.gonzaloracergalan.portfolio.data.dt.dto


import com.gonzaloracergalan.portfolio.data.db.entity.TrabajoEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrabajoDTO(
    @SerialName("fechaFin")
    val fechaFinMillis: Long? = null,
    @SerialName("fechaInicio")
    val fechaInicioMillis: Long? = null,
    @SerialName("logros")
    val logros: List<String>? = null,
    @SerialName("nombre")
    val nombre: String? = null,
    @SerialName("posicion")
    val posicion: String? = null,
    @SerialName("resumen")
    val resumen: String? = null,
    @SerialName("url")
    val url: String? = null
) {
    fun toEntity(id: Long, resumeOwnerId: Long): TrabajoEntity {
        return TrabajoEntity(
            id = id,
            resumeOwnerId = resumeOwnerId,
            fechaFinMillis = fechaFinMillis,
            fechaInicioMillis = fechaInicioMillis,
            logros = logros,
            nombre = nombre,
            posicion = posicion,
            resumen = resumen,
            url = url
        )
    }
}