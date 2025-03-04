package com.gonzaloracergalan.portfolio.data.dt.dto


import com.gonzaloracergalan.portfolio.data.db.entity.PublicacionEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PublicacionDTO(
    @SerialName("editor")
    val editor: String? = null,
    @SerialName("fechaPublicacion")
    val fechaPublicacion: String? = null,
    @SerialName("nombre")
    val nombre: String? = null,
    @SerialName("resumen")
    val resumen: String? = null,
    @SerialName("url")
    val url: String? = null
) {
    fun toEntity(id: Long, resumeOwnerId: Long): PublicacionEntity {
        return PublicacionEntity(
            id = id,
            resumeOwnerId = resumeOwnerId,
            editor = editor,
            fechaPublicacion = fechaPublicacion,
            nombre = nombre,
            resumen = resumen,
            url = url
        )
    }
}