package com.gonzaloracergalan.portfolio.data.dt.dto


import com.gonzaloracergalan.portfolio.data.db.entity.ProyectoEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProyectoDTO(
    @SerialName("descripcion")
    val descripcion: String? = null,
    @SerialName("fechaFin")
    val fechaFin: String? = null,
    @SerialName("fechaInicio")
    val fechaInicio: String? = null,
    @SerialName("logros")
    val logros: List<String>? = null,
    @SerialName("nombre")
    val nombre: String? = null,
    @SerialName("url")
    val url: String? = null
) {
    fun toEntity(id: Long, resumeOwnerId: Long): ProyectoEntity {
        return ProyectoEntity(
            id = id,
            resumeOwnerId = resumeOwnerId,
            descripcion = descripcion,
            fechaFin = fechaFin,
            fechaInicio = fechaInicio,
            logros = logros,
            nombre = nombre,
            url = url
        )
    }
}