package com.gonzaloracergalan.portfolio.ui.model

data class MainActivityModel(
    val sections : Set<Section>
) {
    enum class Section {
        INFORMACION_GENERAL,
        EXPERIENCIA,
        ESTUDIOS,
        PREMIOS_CERTIFICADOS,
        PUBLICACIONES,
        PROYECTOS,
        MAS_SOBRE_MI
    }
}