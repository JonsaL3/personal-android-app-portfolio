package com.gonzaloracergalan.portfolio.data.dt.dto


import com.gonzaloracergalan.portfolio.data.db.entity.PerfilEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PerfilDTO(
    @SerialName("red")
    val red: String? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("usuario")
    val usuario: String? = null
) {
    fun toEntity(id: Long, basicoId: Long): PerfilEntity {
        return PerfilEntity(
            id = id,
            basicoId = basicoId,
            red = red,
            url = url,
            usuario = usuario
        )
    }
}