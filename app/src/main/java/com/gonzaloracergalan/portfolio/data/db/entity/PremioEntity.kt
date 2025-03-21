package com.gonzaloracergalan.portfolio.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.gonzaloracergalan.portfolio.data.dt.dto.PremioDTO

@Entity(
    tableName = "premio",
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
data class PremioEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val resumeOwnerId: Long,
    val fecha: String?,
    val otorgadoPor: String?,
    val resumen: String?,
    val titulo: String?
) {
    fun toDTO(): PremioDTO {
        return PremioDTO(
            fecha = fecha,
            otorgadoPor = otorgadoPor,
            resumen = resumen,
            titulo = titulo
        )
    }
}