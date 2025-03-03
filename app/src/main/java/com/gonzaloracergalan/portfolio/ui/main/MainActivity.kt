package com.gonzaloracergalan.portfolio.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.gonzaloracergalan.portfolio.ui.theme.PortfolioTheme

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

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PortfolioTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                }
            }
        }
    }
}