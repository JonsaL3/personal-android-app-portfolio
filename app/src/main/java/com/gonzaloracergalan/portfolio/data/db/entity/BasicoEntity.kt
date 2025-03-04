package com.gonzaloracergalan.portfolio.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.gonzaloracergalan.portfolio.data.dt.dto.BasicoDTO

@Entity(
    tableName = "basico",
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
data class BasicoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val resumeOwnerId: Long,
    val correo: String?,
    val etiqueta: String?,
    val imagen: String?,
    val nombre: String?,
    val resumen: String?,
    val telefono: String?,
    val url: String?,
    @Embedded
    val ubicacion: UbicacionEntity?
) {
    fun toDTO(perfiles: List<PerfilEntity>): BasicoDTO {
        return BasicoDTO(
            correo = correo,
            etiqueta = etiqueta,
            imagen = imagen,
            nombre = nombre,
            resumen = resumen,
            telefono = telefono,
            url = url,
            ubicacion = ubicacion?.toDTO(),
            perfiles = perfiles.map { it.toDTO() }
        )
    }
}