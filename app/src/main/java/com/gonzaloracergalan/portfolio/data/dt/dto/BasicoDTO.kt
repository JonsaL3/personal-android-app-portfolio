package com.gonzaloracergalan.portfolio.data.dt.dto

import com.gonzaloracergalan.portfolio.data.db.entity.BasicoEntity
import com.gonzaloracergalan.portfolio.data.db.entity.PerfilEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BasicoDTO(
    @SerialName("correo")
    val correo: String? = null,
    @SerialName("etiqueta")
    val etiqueta: String? = null,
    @SerialName("imagen")
    val imagen: String? = null,
    @SerialName("nombre")
    val nombre: String? = null,
    @SerialName("perfiles")
    val perfiles: List<PerfilDTO>? = null,
    @SerialName("resumen")
    val resumen: String? = null,
    @SerialName("telefono")
    val telefono: String? = null,
    @SerialName("ubicacion")
    val ubicacion: UbicacionDTO? = null,
    @SerialName("url")
    val url: String? = null
) {
    fun toEntity(id: Long, resumeOwnerId: Long): Pair<BasicoEntity, List<PerfilEntity>> {
        val basicoEntity = BasicoEntity(
            id = id,
            resumeOwnerId = resumeOwnerId,
            correo = correo,
            etiqueta = etiqueta,
            imagen = imagen,
            nombre = nombre,
            resumen = resumen,
            telefono = telefono,
            url = url,
            ubicacion = ubicacion?.toEntity(),
        )
        // Un basico al estar compuesto por una lista de perfiles, por como está mapeada la base de
        // datos, los perfiles son otra entidad.
        val perfilesEntity = perfiles?.map { it.toEntity(0, id) } ?: emptyList()
        return Pair(basicoEntity, perfilesEntity)
    }
}