package com.gonzaloracergalan.portfolio.domain.model

import java.util.Date

data class Trabajo(
    val fechaFin: Date?,
    val fechaInicio: Date?,
    val logros: List<String>,
    val nombre: String?,
    val posicion: String?,
    val resumen: String?,
    val url: String?
)