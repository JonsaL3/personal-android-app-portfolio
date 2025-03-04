package com.gonzaloracergalan.portfolio.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.gonzaloracergalan.portfolio.data.dt.dto.TrabajoDTO

@Entity(
    tableName = "trabajo",
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
data class TrabajoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val resumeOwnerId: Long,
    val fechaFin: String?,
    val fechaInicio: String?,
    val logros: List<String>?, // Se requiere el converter
    val nombre: String?,
    val posicion: String?,
    val resumen: String?,
    val url: String?
) {
    fun toDTO(): TrabajoDTO {
        return TrabajoDTO(
            fechaFin = fechaFin,
            fechaInicio = fechaInicio,
            logros = logros,
            nombre = nombre,
            posicion = posicion,
            resumen = resumen,
            url = url
        )
    }
}