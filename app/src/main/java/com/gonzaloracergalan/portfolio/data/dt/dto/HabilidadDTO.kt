package com.gonzaloracergalan.portfolio.data.dt.dto


import com.gonzaloracergalan.portfolio.data.db.entity.HabilidadEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HabilidadDTO(
    @SerialName("nivel")
    val nivel: String? = null,
    @SerialName("nombre")
    val nombre: String? = null,
    @SerialName("palabrasClave")
    val palabrasClave: List<String>? = null
) {
    fun toEntity(id: Long, resumeOwnerId: Long): HabilidadEntity {
        return HabilidadEntity(
            id = id,
            resumeOwnerId = resumeOwnerId,
            nivel = nivel,
            nombre = nombre,
            palabrasClave = palabrasClave
        )
    }
}