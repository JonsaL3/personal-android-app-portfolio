package com.gonzaloracergalan.portfolio.data.dt.dto

import androidx.room.Entity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class JsonResumeWrapperDTO(
    @SerialName("basicos")
    val basico: BasicoDTO? = null,
    @SerialName("certificados")
    val certificados: List<CertificadoDTO>? = null,
    @SerialName("educacion")
    val educacion: List<EducacionDTO>? = null,
    @SerialName("habilidades")
    val habilidades: List<HabilidadDTO>? = null,
    @SerialName("idiomas")
    val idiomas: List<IdiomaDTO>? = null,
    @SerialName("intereses")
    val intereses: List<InteresDTO>? = null,
    @SerialName("premios")
    val premios: List<PremioDTO>? = null,
    @SerialName("proyectos")
    val proyectos: List<ProyectoDTO>? = null,
    @SerialName("publicaciones")
    val publicaciones: List<PublicacionDTO>? = null,
    @SerialName("referencias")
    val referencias: List<ReferenciaDTO>? = null,
    @SerialName("trabajo")
    val trabajo: List<TrabajoDTO>? = null,
    @SerialName("voluntariado")
    val voluntariado: List<VoluntariadoDTO>? = null
)