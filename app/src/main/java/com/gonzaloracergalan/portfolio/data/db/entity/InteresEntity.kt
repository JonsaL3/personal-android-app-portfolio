package com.gonzaloracergalan.portfolio.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "interes",
    foreignKeys = [
        ForeignKey(
            entity = JsonResumeWrapperEntity::class,
            parentColumns = ["resumeId"],
            childColumns = ["resumeOwnerId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class InteresEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val resumeOwnerId: Long,
    val nombre: String?,
    val palabrasClave: List<String>? // Se requiere el converter
)