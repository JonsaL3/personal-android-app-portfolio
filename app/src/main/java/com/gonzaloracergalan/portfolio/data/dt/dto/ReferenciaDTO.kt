package com.gonzaloracergalan.portfolio.data.dt.dto


import com.gonzaloracergalan.portfolio.data.db.entity.ReferenciaEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReferenciaDTO(
    @SerialName("nombre")
    val nombre: String? = null,
    @SerialName("referencia")
    val referencia: String? = null
) {
    fun toEntity(id: Long, resumeOwnerId: Long): ReferenciaEntity {
        return ReferenciaEntity(
            id = id,
            resumeOwnerId = resumeOwnerId,
            nombre = nombre,
            referencia = referencia
        )
    }
}