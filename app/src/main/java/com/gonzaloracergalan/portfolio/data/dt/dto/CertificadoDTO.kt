package com.gonzaloracergalan.portfolio.data.dt.dto


import com.gonzaloracergalan.portfolio.data.db.entity.CertificadoEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CertificadoDTO(
    @SerialName("emisor")
    val emisor: String? = null,
    @SerialName("fecha")
    val fecha: String? = null,
    @SerialName("nombre")
    val nombre: String? = null,
    @SerialName("url")
    val url: String? = null
) {
    fun toEntity(id: Long, resumeOwnerId: Long): CertificadoEntity {
        return CertificadoEntity(
            id = id,
            resumeOwnerId = resumeOwnerId,
            emisor = emisor,
            fecha = fecha,
            nombre = nombre,
            url = url,
        )
    }
}