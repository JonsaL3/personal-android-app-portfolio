package com.gonzaloracergalan.portfolio.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.gonzaloracergalan.portfolio.data.dt.dto.ProyectoDTO

@Entity(
    tableName = "proyecto",
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
data class ProyectoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val resumeOwnerId: Long,
    val descripcion: String?,
    val fechaFin: String?,
    val fechaInicio: String?,
    val logros: List<String>?, // Se requiere el converter
    val nombre: String?,
    val url: String?
) {
    fun toDTO(): ProyectoDTO {
        return ProyectoDTO(
            descripcion = descripcion,
            fechaFin = fechaFin,
            fechaInicio = fechaInicio,
            logros = logros,
            nombre = nombre,
            url = url
        )
    }
}