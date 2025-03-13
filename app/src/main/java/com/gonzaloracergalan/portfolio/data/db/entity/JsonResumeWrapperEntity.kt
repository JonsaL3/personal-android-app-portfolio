package com.gonzaloracergalan.portfolio.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gonzaloracergalan.portfolio.data.dt.dto.JsonResumeWrapperDTO

@Entity(
    tableName = "resumes",
)
data class JsonResumeWrapperEntity(
    @PrimaryKey(autoGenerate = true)
    val resumeId: Long = 0,
    // Solo uno podra ser isCurrent = true, que sera del que veamos la información en
    // pantalla, el repo debera de garantizar que solo haya 1 a true.
    val isCurrent: Boolean = false,
) {
    fun toDTO(
        basicoEntity: BasicoEntity,
        perfilesEntity: List<PerfilEntity>,
        certificacionEntities: List<CertificadoEntity>,
        educacionEntities: List<EducacionEntity>,
        habilidadEntities: List<HabilidadEntity>,
        idiomaEntities: List<IdiomaEntity>,
        interesEntities: List<InteresEntity>,
        premiosEntities: List<PremioEntity>,
        proyectosEntities: List<ProyectoEntity>,
        publicacionesEntities: List<PublicacionEntity>,
        referenciasEntities: List<ReferenciaEntity>,
        trabajoEntities: List<TrabajoEntity>,
        voluntariadoEntities: List<VoluntariadoEntity>
    ): JsonResumeWrapperDTO {
        return JsonResumeWrapperDTO(
            basico = basicoEntity.toDTO(perfilesEntity),
            certificados = certificacionEntities.map { it.toDTO() },
            educacion = educacionEntities.map { it.toDTO() },
            habilidades = habilidadEntities.map { it.toDTO() },
            idiomas = idiomaEntities.map { it.toDTO() },
            intereses = interesEntities.map { it.toDTO() },
            premios = premiosEntities.map { it.toDTO() },
            proyectos = proyectosEntities.map { it.toDTO() },
            publicaciones = publicacionesEntities.map { it.toDTO() },
            referencias = referenciasEntities.map { it.toDTO() },
            trabajo = trabajoEntities.map { it.toDTO() },
            voluntariado = voluntariadoEntities.map { it.toDTO() }
        )
    }
}