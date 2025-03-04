package com.gonzaloracergalan.portfolio.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.gonzaloracergalan.portfolio.data.dt.dto.VoluntariadoDTO

@Entity(
    tableName = "voluntariado",
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
) {
    fun toDTO(): VoluntariadoDTO {
        return VoluntariadoDTO(
            fechaFin = fechaFin,
            fechaInicio = fechaInicio,
            logros = logros,
            organizacion = organizacion,
            posicion = posicion,
            resumen = resumen,
            url = url
        )
    }
}