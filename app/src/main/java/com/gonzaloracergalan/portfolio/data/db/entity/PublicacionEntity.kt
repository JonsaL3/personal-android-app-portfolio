package com.gonzaloracergalan.portfolio.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.gonzaloracergalan.portfolio.data.dt.dto.PublicacionDTO

@Entity(
    tableName = "publicacion",
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
data class PublicacionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val resumeOwnerId: Long,
    val editor: String?,
    val fechaPublicacion: String?,
    val nombre: String?,
    val resumen: String?,
    val url: String?
) {
    fun toDTO(): PublicacionDTO {
        return PublicacionDTO(
            editor = editor,
            fechaPublicacion = fechaPublicacion,
            nombre = nombre,
            resumen = resumen,
            url = url
        )
    }
}