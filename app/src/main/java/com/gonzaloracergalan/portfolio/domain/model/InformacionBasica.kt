package com.gonzaloracergalan.portfolio.domain.model

data class InformacionBasica(
    val correo: String?,
    val cargoActual: String?,
    val imagen: String?,
    val nombre: String?,
    val resumen: String?,
    val telefono: String?,
    val direccion: Direccion?,
    val redesSociales: List<RedSocial>,
)