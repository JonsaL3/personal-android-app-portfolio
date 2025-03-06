package com.gonzaloracergalan.portfolio.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
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
import com.gonzaloracergalan.portfolio.data.repository.JsonResumeWrapperRepository
import com.gonzaloracergalan.portfolio.ui.theme.PortfolioTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.slf4j.LoggerFactory

/**
 * Aplicación orientada a crear mi portfolio personal como desarrollador de android.
 * Esta será dinamica para su facil mantenimiento, basandonos en el proyecto open source
 * json-resume: https://jsonresume.org/
 *
 * La aplicación estará sujeta al estandar definido en json-resume, el cual se muestra a continuación,
 * el cual es una mera traducción del inglés para mayor legibilidad para mi:
 * {
 *   "basicos": {
 *     "nombre": "John Doe",
 *     "etiqueta": "Programador",
 *     "imagen": "",
 *     "correo": "john@gmail.com",
 *     "telefono": "(912) 555-4321",
 *     "url": "https://johndoe.com",
 *     "resumen": "Un resumen de John Doe…",
 *     "ubicacion": {
 *       "direccion": "2712 Broadway St",
 *       "codigoPostal": "CA 94115",
 *       "ciudad": "San Francisco",
 *       "codigoPais": "US",
 *       "region": "California"
 *     },
 *     "perfiles": [
 *       {
 *         "red": "Twitter",
 *         "usuario": "john",
 *         "url": "https://twitter.com/john"
 *       }
 *     ]
 *   },
 *   "trabajo": [
 *     {
 *       "nombre": "Empresa",
 *       "posicion": "Presidente",
 *       "url": "https://company.com",
 *       "fechaInicio": "2013-01-01",
 *       "fechaFin": "2014-01-01",
 *       "resumen": "Descripcion…",
 *       "logros": [
 *         "Inicio la empresa"
 *       ]
 *     }
 *   ],
 *   "voluntariado": [
 *     {
 *       "organizacion": "Organizacion",
 *       "posicion": "Voluntario",
 *       "url": "https://organization.com/",
 *       "fechaInicio": "2012-01-01",
 *       "fechaFin": "2013-01-01",
 *       "resumen": "Descripcion…",
 *       "logros": [
 *         "Premiado como 'Voluntario del Mes'"
 *       ]
 *     }
 *   ],
 *   "educacion": [
 *     {
 *       "institucion": "Universidad",
 *       "url": "https://institution.com/",
 *       "area": "Desarrollo de Software",
 *       "tipoEstudio": "Licenciatura",
 *       "fechaInicio": "2011-01-01",
 *       "fechaFin": "2013-01-01",
 *       "calificacion": "4.0",
 *       "cursos": [
 *         "DB1101 - SQL Basico"
 *       ]
 *     }
 *   ],
 *   "premios": [
 *     {
 *       "titulo": "Premio",
 *       "fecha": "2014-11-01",
 *       "otorgadoPor": "Empresa",
 *       "resumen": "No hay cuchara."
 *     }
 *   ],
 *   "certificados": [
 *     {
 *       "nombre": "Certificado",
 *       "fecha": "2021-11-07",
 *       "emisor": "Empresa",
 *       "url": "https://certificate.com"
 *     }
 *   ],
 *   "publicaciones": [
 *     {
 *       "nombre": "Publicacion",
 *       "editor": "Empresa",
 *       "fechaPublicacion": "2014-10-01",
 *       "url": "https://publication.com",
 *       "resumen": "Descripcion…"
 *     }
 *   ],
 *   "habilidades": [
 *     {
 *       "nombre": "Desarrollo Web",
 *       "nivel": "Maestro",
 *       "palabrasClave": [
 *         "HTML",
 *         "CSS",
 *         "JavaScript"
 *       ]
 *     }
 *   ],
 *   "idiomas": [
 *     {
 *       "idioma": "Ingles",
 *       "fluidez": "Nativo"
 *     }
 *   ],
 *   "intereses": [
 *     {
 *       "nombre": "Vida salvaje",
 *       "palabrasClave": [
 *         "Hurones",
 *         "Unicornios"
 *       ]
 *     }
 *   ],
 *   "referencias": [
 *     {
 *       "nombre": "Jane Doe",
 *       "referencia": "Referencia…"
 *     }
 *   ],
 *   "proyectos": [
 *     {
 *       "nombre": "Proyecto",
 *       "fechaInicio": "2019-01-01",
 *       "fechaFin": "2021-01-01",
 *       "descripcion": "Descripcion...",
 *       "logros": [
 *         "Gano premio en AIHacks 2016"
 *       ],
 *       "url": "https://project.com/"
 *     }
 *   ]
 * }
 */
class MainActivity : ComponentActivity() {

    companion object {
        private val logger = LoggerFactory.getLogger("MainActivity")
    }

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        logger.info("onCreate")
        logger.warn("onCreate")
        logger.error("onCreate")
        logger.debug("onCreate")
        logger.trace("onCreate")

        pepe()

        setContent {
            PortfolioTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                }
            }
        }
    }

    // todo borrar
    // todo borrar
    // todo borrar
    // todo borrar
    private val jsonResumeWrapperRepository: JsonResumeWrapperRepository by inject()
    private fun pepe() {
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
                        region = "example",
                        ciudad = "example",
                        codigoPais = "example"
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
                        resumen = "example2",
                        url = "example2"
                    )
                )
            )
            jsonResumeWrapperRepository.save(dto = exampleDTO)
        }
    }

}