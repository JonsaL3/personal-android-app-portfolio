package com.gonzaloracergalan.portfolio.ui.model

import kotlinx.serialization.SerialName
import java.time.LocalDate

data class ExperienciaModel(
    val trabajos: List<Trabajo>,
    val voluntariados: List<Voluntariado>
) {
    data class Trabajo(
        val fechaFin: LocalDate?,
        val fechaInicio: LocalDate?,
        val logros: List<String>,
        val nombre: String?,
        val posicion: String?,
        val resumen: String?,
        val url: String?
    )
    data class Voluntariado(
        val fechaFin: LocalDate?,
        val fechaInicio: LocalDate?,
        val logros: List<String>?,
        val organizacion: String?,
        val posicion: String?,
        val resumen: String?,
        val url: String?
    )
}