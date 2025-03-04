package com.gonzaloracergalan.portfolio.data.db.entity

import com.gonzaloracergalan.portfolio.data.dt.dto.UbicacionDTO

data class UbicacionEntity(
    val ciudad: String?,
    val codigoPais: String?,
    val codigoPostal: String?,
    val direccion: String?,
    val region: String?
) {
    fun toDTO(): UbicacionDTO {
        return UbicacionDTO(
            ciudad = ciudad,
            codigoPais = codigoPais,
            codigoPostal = codigoPostal,
            direccion = direccion,
            region = region
        )
    }
}