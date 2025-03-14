package com.gonzaloracergalan.portfolio.ui.model

data class InformacionGeneralUI(
    val correo: String,
    val cargoActual: String,
    val imagen: String?,
    val nombre: String,
    val redesSociales: List<RedSocialUI> = emptyList(),
    val resumen: String,
    val telefono: String,
    val direccion: DireccionUI?
)