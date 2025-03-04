package com.gonzaloracergalan.portfolio.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.gonzaloracergalan.portfolio.data.dt.dto.EducacionDTO

@Entity(
    tableName = "educacion",
    foreignKeys = [
        ForeignKey(
            entity = JsonResumeWrapperEntity::class,
            parentColumns = ["resumeId"],
            childColumns = ["resumeOwnerId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["resumeOwnerId"])
    ]
)
data class EducacionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val resumeOwnerId: Long,
    val area: String?,
    val calificacion: String?,
    val cursos: List<String>?, // Se requiere el converter
    val fechaFin: String?,
    val fechaInicio: String?,
    val institucion: String?,
    val tipoEstudio: String?,
    val url: String?
) {
    fun toDTO(): EducacionDTO {
        return EducacionDTO(
            area = area,
            calificacion = calificacion,
            cursos = cursos,
            fechaFin = fechaFin,
            fechaInicio = fechaInicio,
            institucion = institucion,
            tipoEstudio = tipoEstudio,
            url = url
        )
    }
}