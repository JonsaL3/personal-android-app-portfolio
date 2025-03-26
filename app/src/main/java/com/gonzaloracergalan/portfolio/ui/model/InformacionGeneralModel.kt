package com.gonzaloracergalan.portfolio.ui.model

data class InformacionGeneralModel(
    val correo: String?,
    val cargoActual: String?,
    val imagen: String?,
    val nombre: String?,
    val resumen: String?,
    val telefono: String?,
    val ciudad: String?,
    val codigoPais: String?,
    val region: String?,
    val codigoPostal: String?,
    val direccion: String?,
    val redesSociales: List<RedSocial> = emptyList()
) {
    data class RedSocial(
        val nombre: String?,
        val url: String?,
        val usuario: String?
    )
}