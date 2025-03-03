package com.gonzaloracergalan.portfolio.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "perfil",
    foreignKeys = [
        ForeignKey(
            entity = BasicoEntity::class,
            parentColumns = ["id"],
            childColumns = ["basicoId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PerfilEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val basicoId: Long,
    val red: String?,
    val url: String?,
    val usuario: String?
)