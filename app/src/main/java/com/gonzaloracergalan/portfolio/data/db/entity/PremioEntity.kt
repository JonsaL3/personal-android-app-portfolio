package com.gonzaloracergalan.portfolio.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "premio",
    foreignKeys = [
        ForeignKey(
            entity = JsonResumeWrapperEntity::class,
            parentColumns = ["resumeId"],
            childColumns = ["resumeOwnerId"],
            onDelete = ForeignKey.CASCADE
        )
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
)