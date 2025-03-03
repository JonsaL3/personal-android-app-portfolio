package com.gonzaloracergalan.portfolio.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "referencia",
    foreignKeys = [
        ForeignKey(
            entity = JsonResumeWrapperEntity::class,
            parentColumns = ["resumeId"],
            childColumns = ["resumeOwnerId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ReferenciaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val resumeOwnerId: Long,
    val nombre: String?,
    val referencia: String?
)