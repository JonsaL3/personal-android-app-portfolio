package com.gonzaloracergalan.portfolio.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.gonzaloracergalan.portfolio.data.dt.dto.HabilidadDTO

@Entity(
    tableName = "habilidad",
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
data class HabilidadEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val resumeOwnerId: Long,
    val nivel: String?,
    val nombre: String?,
    val palabrasClave: List<String>? // Se requiere el converter
) {
    fun toDTO(): HabilidadDTO {
        return HabilidadDTO(
            nivel = nivel,
            nombre = nombre,
            palabrasClave = palabrasClave
        )
    }
}