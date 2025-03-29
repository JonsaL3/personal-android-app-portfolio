package com.gonzaloracergalan.portfolio.domain.model

import java.time.LocalDate

data class Voluntariado(
    val fechaFin: LocalDate?,
    val fechaInicio: LocalDate?,
    val logros: List<String>?,
    val organizacion: String?,
    val posicion: String?,
    val resumen: String?,
    val url: String?
)