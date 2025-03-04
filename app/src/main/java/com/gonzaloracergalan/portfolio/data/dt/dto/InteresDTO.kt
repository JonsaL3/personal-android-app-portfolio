package com.gonzaloracergalan.portfolio.data.dt.dto


import com.gonzaloracergalan.portfolio.data.db.entity.InteresEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InteresDTO(
    @SerialName("nombre")
    val nombre: String? = null,
    @SerialName("palabrasClave")
    val palabrasClave: List<String>? = null
) {
    fun toEntity(id: Long, resumeOwnerId: Long): InteresEntity {
        return InteresEntity(
            id = id,
            resumeOwnerId = resumeOwnerId,
            nombre = nombre,
            palabrasClave = palabrasClave
        )
    }
}