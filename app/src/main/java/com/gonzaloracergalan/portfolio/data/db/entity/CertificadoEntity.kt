package com.gonzaloracergalan.portfolio.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.gonzaloracergalan.portfolio.data.dt.dto.CertificadoDTO

@Entity(
    tableName = "certificado",
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
data class CertificadoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val resumeOwnerId: Long,
    val emisor: String?,
    val fecha: String?,
    val nombre: String?,
    val url: String?
) {
    fun toDTO(): CertificadoDTO {
        return CertificadoDTO(
            emisor = emisor,
            fecha = fecha,
            nombre = nombre,
            url = url
        )
    }
}