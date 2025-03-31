package com.gonzaloracergalan.portfolio.ui.model

import java.util.Date

data class ExperienciaModel(
    val trabajos: List<Trabajo>,
    val voluntariados: List<Voluntariado>
) {
    data class Trabajo(
        val fechaFin: Date?,
        val fechaInicio: Date?,
        val logros: List<String>,
        val nombre: String?,
        val posicion: String?,
        val resumen: String?,
        val url: String?
    )
    data class Voluntariado(
        val fechaFin: Date?,
        val fechaInicio: Date?,
        val logros: List<String>?,
        val organizacion: String?,
        val posicion: String?,
        val resumen: String?,
        val url: String?
    )
}