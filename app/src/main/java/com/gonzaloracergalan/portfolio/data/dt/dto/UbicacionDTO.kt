package com.gonzaloracergalan.portfolio.data.dt.dto


import com.gonzaloracergalan.portfolio.data.db.entity.UbicacionEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UbicacionDTO(
    @SerialName("ciudad")
    val ciudad: String? = null,
    @SerialName("codigoPais")
    val codigoPais: String? = null,
    @SerialName("codigoPostal")
    val codigoPostal: String? = null,
    @SerialName("direccion")
    val direccion: String? = null,
    @SerialName("region")
    val region: String? = null
) {
    fun toEntity(): UbicacionEntity {
        return UbicacionEntity(
            ciudad = ciudad,
            codigoPais = codigoPais,
            codigoPostal = codigoPostal,
            direccion = direccion,
            region = region
        )
    }
}