package com.gonzaloracergalan.portfolio.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "voluntariado",
    foreignKeys = [
        ForeignKey(
            entity = JsonResumeWrapperEntity::class,
            parentColumns = ["resumeId"],
            childColumns = ["resumeOwnerId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class VoluntariadoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val resumeOwnerId: Long,
    val fechaFin: String?,
    val fechaInicio: String?,
    val logros: List<String>?, // Se requiere el converter
    val organizacion: String?,
    val posicion: String?,
    val resumen: String?,
    val url: String?
)