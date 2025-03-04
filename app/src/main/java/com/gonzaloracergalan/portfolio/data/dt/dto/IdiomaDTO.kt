package com.gonzaloracergalan.portfolio.data.dt.dto


import com.gonzaloracergalan.portfolio.data.db.entity.IdiomaEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IdiomaDTO(
    @SerialName("fluidez")
    val fluidez: String? = null,
    @SerialName("idioma")
    val idioma: String? = null
) {
    fun toEntity(id: Long, resumeOwnerId: Long): IdiomaEntity {
        return IdiomaEntity(
            id = id,
            resumeOwnerId = resumeOwnerId,
            fluidez = fluidez,
            idioma = idioma
        )
    }
}