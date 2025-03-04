package com.gonzaloracergalan.portfolio.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.gonzaloracergalan.portfolio.data.dt.dto.IdiomaDTO

@Entity(
    tableName = "idioma",
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
data class IdiomaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val resumeOwnerId: Long,
    val fluidez: String?,
    val idioma: String?
) {
    fun toDTO(): IdiomaDTO {
        return IdiomaDTO(
            fluidez = fluidez,
            idioma = idioma
        )
    }
}