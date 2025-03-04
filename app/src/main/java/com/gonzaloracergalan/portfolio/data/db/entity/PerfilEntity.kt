package com.gonzaloracergalan.portfolio.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
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
    ],
    indices = [
        Index(value = ["basicoId"])
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