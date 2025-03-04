package com.gonzaloracergalan.portfolio.data.repository.util

import com.gonzaloracergalan.portfolio.data.dt.dto.BasicoDTO
import com.gonzaloracergalan.portfolio.data.dt.dto.CertificadoDTO
import com.gonzaloracergalan.portfolio.data.dt.dto.EducacionDTO
import com.gonzaloracergalan.portfolio.data.dt.dto.HabilidadDTO
import com.gonzaloracergalan.portfolio.data.dt.dto.IdiomaDTO
import com.gonzaloracergalan.portfolio.data.dt.dto.InteresDTO
import com.gonzaloracergalan.portfolio.data.dt.dto.JsonResumeWrapperDTO
import com.gonzaloracergalan.portfolio.data.dt.dto.PerfilDTO
import com.gonzaloracergalan.portfolio.data.dt.dto.PremioDTO
import com.gonzaloracergalan.portfolio.data.dt.dto.ProyectoDTO
import com.gonzaloracergalan.portfolio.data.dt.dto.PublicacionDTO
import com.gonzaloracergalan.portfolio.data.dt.dto.ReferenciaDTO
import com.gonzaloracergalan.portfolio.data.dt.dto.TrabajoDTO
import com.gonzaloracergalan.portfolio.data.dt.dto.UbicacionDTO
import com.gonzaloracergalan.portfolio.data.dt.dto.VoluntariadoDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// TODO BORRAR
// TODO BORRAR
// TODO BORRAR
// TODO BORRAR
// TODO BORRAR
object Example {
    // TODO BORRAR
    // TODO BORRAR
    // TODO BORRAR
    // TODO BORRAR
    // TODO BORRAR
    init {
        CoroutineScope(Dispatchers.IO).launch {
            val exampleDTO = JsonResumeWrapperDTO(
                basico = BasicoDTO(
                    correo = "example@example.es",
                    etiqueta = "example",
                    imagen = "example",
                    nombre = "example",
                    perfiles = listOf(
                        PerfilDTO("example", "example", "example"),
                        PerfilDTO("example2", "example2", "example2")
                    ),
                    resumen = "example",
                    telefono = "example",
                    ubicacion = UbicacionDTO(
                        codigoPostal = "example",
                        direccion = "example",
                        region = "example"
                    ),
                    url = "example"
                ),
                certificados = listOf(
                    CertificadoDTO("example", "example", "example", "example"),
                    CertificadoDTO("example2", "example2", "example2", "example2"),
                    CertificadoDTO("example3", "example3", "example3", "example3")
                ),
                educacion = listOf(
                    EducacionDTO(
                        area = "example",
                        calificacion = "example",
                        cursos = listOf("example", "example", "example"),
                        fechaInicio = "example",
                        fechaFin = "example",
                        institucion = "example",
                        tipoEstudio = "example",
                        url = "example",
                    ),
                    EducacionDTO(
                        area = "example2",
                        calificacion = "example2",
                        cursos = listOf("example2", "example2", "example2"),
                        fechaInicio = "example2",
                        fechaFin = "example2",
                        institucion = "example2",
                        tipoEstudio = "example2",
                        url = "example2",
                    ),
                ),
                habilidades = listOf(
                    HabilidadDTO("example", "example", listOf("example", "example", "example")),
                    HabilidadDTO(
                        "example2",
                        "example2",
                        listOf("example2", "example2", "example2")
                    ),
                    HabilidadDTO("example3", "example3", listOf("example3", "example3", "example3"))
                ),
                idiomas = listOf(
                    IdiomaDTO("example", "example"),
                    IdiomaDTO("example2", "example2"),
                    IdiomaDTO("example3", "example3")
                ),
                intereses = listOf(
                    InteresDTO("example", listOf("example", "example", "example")),
                    InteresDTO("example2", listOf("example2", "example2", "example2")),
                ),
                premios = listOf(
                    PremioDTO("example", "example", "example", "example"),
                    PremioDTO("example2", "example2", "example2", "example2"),
                    PremioDTO("example3", "example3", "example3", "example3")
                ),
                proyectos = listOf(
                    ProyectoDTO(
                        descripcion = "example",
                        fechaInicio = "example",
                        fechaFin = "example",
                        nombre = "example",
                        url = "example",
                    ),
                    ProyectoDTO(
                        descripcion = "example2",
                        fechaInicio = "example2",
                        fechaFin = "example2",
                        nombre = "example2",
                        url = "example2",
                    ),
                ),
                publicaciones = listOf(
                    PublicacionDTO(
                        nombre = "example",
                        url = "example",
                        editor = "example",
                        fechaPublicacion = "example",
                        resumen = "example"
                    ),
                    PublicacionDTO(
                        nombre = "example2",
                        url = "example2",
                        editor = "example2",
                        fechaPublicacion = "example2",
                        resumen = "example2"
                    ),
                    PublicacionDTO(
                        nombre = "example3",
                        url = "example3",
                        editor = "example3",
                        fechaPublicacion = "example3",
                        resumen = "example3"
                    )
                ),
                referencias = listOf(
                    ReferenciaDTO("example", "example"),
                    ReferenciaDTO("example2", "example2"),
                    ReferenciaDTO("example3", "example3"),
                    ReferenciaDTO("example4", "example4"),
                ),
                trabajo = listOf(
                    TrabajoDTO(
                        fechaInicio = "example",
                        fechaFin = "example",
                        logros = listOf("example", "example", "example"),
                        nombre = "example",
                        posicion = "example",
                        resumen = "example",
                        url = "example"
                    ),
                    TrabajoDTO(
                        fechaInicio = "example2",
                        fechaFin = "example2",
                        logros = listOf("example2", "example2", "example2"),
                        nombre = "example2",
                        posicion = "example2",
                        resumen = "example2"
                    )
                ),
                voluntariado = listOf(
                    VoluntariadoDTO(
                        fechaFin = "example",
                        fechaInicio = "example",
                        logros = listOf("example", "example", "example"),
                        organizacion = "example",
                        posicion = "example",
                        resumen = "example",
                        url = "example"
                    ),
                    VoluntariadoDTO(
                        fechaFin = "example2",
                        fechaInicio = "example2",
                        logros = listOf("example2", "example2", "example2"),
                        organizacion = "example2",
                        posicion = "example2",
                        resumen = "example2"
                    )
                )
            )

        }
    }

}