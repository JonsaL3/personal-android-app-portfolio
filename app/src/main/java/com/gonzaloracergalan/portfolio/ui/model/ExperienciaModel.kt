package com.gonzaloracergalan.portfolio.ui.model

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
    ) {
        fun getFormattedFecha(): String? {
            var formattedStringDate: String? = null
            val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            if (fechaInicio != null && fechaFin != null) {
                formattedStringDate = "${format.format(fechaInicio)} - ${format.format(fechaFin)}"
            }
            return formattedStringDate
        }
    }

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