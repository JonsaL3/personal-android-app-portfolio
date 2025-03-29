package com.gonzaloracergalan.portfolio.domain.model

import java.time.LocalDate

data class Trabajo(
    val fechaFin: LocalDate?,
    val fechaInicio: LocalDate?,
    val logros: List<String>,
    val nombre: String?,
    val posicion: String?,
    val resumen: String?,
    val url: String?
)