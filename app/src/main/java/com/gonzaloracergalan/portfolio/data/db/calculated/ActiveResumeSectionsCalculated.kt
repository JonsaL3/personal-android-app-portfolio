package com.gonzaloracergalan.portfolio.data.db.calculated

data class ActiveResumeSectionsCalculated(
    val resumeId: Long,
    val sections: List<Section>
) {
    enum class Section {
        BASICO,
        CERTIFICADOS,
        EDUCACION,
        HABILIDAD,
        PREMIO,
        IDIOMA,
        INTERES,
        PROYECTO,
        PUBLICACION,
        REFERENCIA,
        TRABAJO,
        VOLUNTARIADO,
    }
}