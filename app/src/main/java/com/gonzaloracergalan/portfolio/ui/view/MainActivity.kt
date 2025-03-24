package com.gonzaloracergalan.portfolio.ui.view

import android.graphics.BitmapFactory.Options
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.gonzaloracergalan.portfolio.data.util.Example
import com.gonzaloracergalan.portfolio.ui.navigation.MainContainerNavigationWrapper
import com.gonzaloracergalan.portfolio.ui.theme.PortfolioTheme
import com.gonzaloracergalan.portfolio.ui.view.component.TransparentCircledTopBar
import com.gonzaloracergalan.portfolio.ui.viewmodel.MainViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
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
class MainActivity : ComponentActivity(), KoinComponent {
    companion object {
        private val logger = LoggerFactory.getLogger("MainActivity")
    }

    private val mainViewModel: MainViewModel by viewModels()

    // todo borrar example
    private val example: Example by inject()
    // todo borrar example

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logger.info("onCreate")
        // todo borrar example
        example
        mainViewModel.setCurrentResumeId(4L)
        // todo borrar example
        enableEdgeToEdge()
        setContent {
            PortfolioTheme {
                // No meto la topbar en el scaffold porque es custom y va a tener cosas que no
                // terminan de adaptarse
                Scaffold { padding -> MainActivityScreen(padding) }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        logger.info("onPause")
    }

    override fun onResume() {
        super.onResume()
        logger.info("onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        logger.info("onDestroy")
    }

    override fun onStart() {
        super.onStart()
        logger.info("onStart")
    }
}

@Composable
private fun MainActivityScreen(paddingValues: PaddingValues) {
    val navHostController = rememberNavController()

    // apertura del drawer
    var isDrawerOpen by remember { mutableStateOf(false) }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val drawerWidth = screenWidth * 0.8f
    val offsetX by animateDpAsState(
        targetValue = if (isDrawerOpen) drawerWidth else 0.dp,
        animationSpec = tween(durationMillis = 500)
    )

    // fundido a blanco y negro ligado al menu hamburguesa
    val fraction by remember {
        derivedStateOf {
            (offsetX.value / drawerWidth.value).coerceIn(0f, 1f)
        }
    }
    val saturation = 1f - fraction
    val colorMatrix = ColorMatrix().apply { setToSaturation(saturation) }
    val darkenAlpha = fraction * 0.6f

    // Blur ligado al menu hambuguesa
    val maxBlur = 4.dp
    val blurRadius = (maxBlur * fraction)

    // scale ligado al menu hamburguesa
    val scale = 1f

    // Contenedor principal menu hamburguesa + vista principal con su navegación
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        // Menu lateral
        HamburguerMenuOptions(
            modifier = Modifier
                .fillMaxSize()
                .shadow(16.dp)
                .background(MaterialTheme.colorScheme.primary)
                .padding(paddingValues),
            options = mapOf() // TODO
        )
        // Vista principal
        MainContent(
            modifier = Modifier
                .fillMaxSize()
                .offset(x = offsetX)
                .blur(blurRadius)
                .drawWithCache {
                    onDrawWithContent {
                        drawIntoCanvas { canvas ->
                            val paint = Paint().apply {
                                colorFilter = ColorFilter.colorMatrix(colorMatrix)
                            }
                            canvas.saveLayer(size.toRect(), paint)
                            drawContent()
                            drawRect(color = Color.Black.copy(alpha = darkenAlpha))
                            canvas.restore()
                        }
                    }
                }
                .scale(scale = scale),
            paddingValues = paddingValues,
            navHostController = navHostController,
            onHambuguerClicked = { isDrawerOpen = !isDrawerOpen },
            onPhoneNumerClicked = { /* TODO */ },
            onEmailClicked = { /* TODO */ },
        )
    }
}

/**
 * Opciones del menú hamburguesa
 */
@Composable
private fun HamburguerMenuOptions(
    modifier: Modifier,
    options: Map<String, () -> Unit>
) {
    Box(modifier) {
        Text(
            text = "Menú",
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 20.sp
        )
    }
}

/**
 * Contenido principal
 */
@Composable
private fun MainContent(
    modifier: Modifier,
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    onHambuguerClicked: () -> Unit,
    onPhoneNumerClicked: () -> Unit,
    onEmailClicked: () -> Unit
) {
    Box(modifier) {
        // Pintamos encima de tod.o el contenido
        MainContainerNavigationWrapper(
            navController = navHostController,
            paddingValues = PaddingValues(
                vertical = paddingValues.calculateTopPadding() + 72.dp,
                horizontal = 16.dp
            )
        )
        // la topbar ha de estar superpuesta al contenido
        TransparentCircledTopBar(
            modifier = Modifier.padding(
                top = paddingValues.calculateTopPadding(),
                start = 16.dp,
                end = 16.dp
            ),
            onHamburguerClicked = onHambuguerClicked,
            onPhoneNumerClicked = onPhoneNumerClicked,
            onEmailClicked = onEmailClicked
        )
    }
}