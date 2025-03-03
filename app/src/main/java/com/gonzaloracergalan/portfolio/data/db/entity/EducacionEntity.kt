package com.gonzaloracergalan.portfolio.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "educacion",
    foreignKeys = [
        ForeignKey(
            entity = JsonResumeWrapperEntity::class,
            parentColumns = ["resumeId"],
            childColumns = ["resumeOwnerId"],
            onDelete = ForeignKey.CASCADE
        )
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
)