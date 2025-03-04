package com.gonzaloracergalan.portfolio.data.dt.dto


import com.gonzaloracergalan.portfolio.data.db.entity.PremioEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PremioDTO(
    @SerialName("fecha")
    val fecha: String? = null,
    @SerialName("otorgadoPor")
    val otorgadoPor: String? = null,
    @SerialName("resumen")
    val resumen: String? = null,
    @SerialName("titulo")
    val titulo: String? = null
) {
    fun toEntity(id: Long, resumeOwnerId: Long): PremioEntity {
        return PremioEntity(
            id = id,
            resumeOwnerId = resumeOwnerId,
            fecha = fecha,
            otorgadoPor = otorgadoPor,
            resumen = resumen,
            titulo = titulo
        )
    }
}