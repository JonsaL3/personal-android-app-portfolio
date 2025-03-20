package com.gonzaloracergalan.portfolio.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gonzaloracergalan.portfolio.R
import com.gonzaloracergalan.portfolio.ui.model.BottomBarUI
import com.gonzaloracergalan.portfolio.ui.navigation.MainContainerNavigationRoutes
import com.gonzaloracergalan.portfolio.ui.navigation.MainContainerNavigationWrapper
import com.gonzaloracergalan.portfolio.ui.theme.PortfolioTheme
import com.gonzaloracergalan.portfolio.ui.view.component.FloatingBottomBar
import com.gonzaloracergalan.portfolio.ui.view.component.FloatingBottomBarItem
import com.gonzaloracergalan.portfolio.ui.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
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

    private val mainViewModel: MainViewModel by viewModels()

    private val showBottomBarRoutes = listOf(
        MainContainerNavigationRoutes.InformacionGeneralRoute.route,
        MainContainerNavigationRoutes.EstudiosRoute.route,
        MainContainerNavigationRoutes.ExperienciaRoute.route,
        MainContainerNavigationRoutes.MasSobreMiRoute.route
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logger.info("onCreate")
        enableEdgeToEdge()

        mainViewModel.setCurrentResumeId(1L)

        // todo temporal
        // todo temporal
        /*var bool = false
        CoroutineScope(Dispatchers.IO).launch {
            while(isActive) {
                delay(1000)
                bool = !bool
                if (bool) {
                    mainViewModel.setCurrentResumeId(1L)
                } else {
                    mainViewModel.setCurrentResumeId(100L)
                }
            }
        }*/
        // todo temporal
        // todo temporal

        setContent {
            PortfolioTheme {
                val navHostController = rememberNavController()
                logger.debug("onCreate Current destination: ${navHostController.currentDestination?.route}")
                Scaffold(
                    modifier = Modifier.background(Color.White),
                ) { padding ->
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        MainContainerNavigationWrapper(navHostController, padding)
                        // no pongo la bottombar en el scaffold por un simple motivo, y es que no ocupa toda la parte inferior, es flotante y quiero que se muestre
                        // encima de la vista. Si la pongo en el scaffold, se mostrará debajo de la vista y se verá feo.
                        MainBottomBar(
                            navController = navHostController,
                            paddingValues = PaddingValues(16.dp),
                            isShow = showBottomBarRoutes.contains(navHostController.currentDestination?.route ?: MainContainerNavigationRoutes.InformacionGeneralRoute.route)
                        )
                    }
                }
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
fun MainBottomBar(
    navController: NavController,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    isShow: Boolean = true
) {
    if (isShow) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        // Elementos de nuestra BottomBar
        val bottomBarItems = listOf(
            // TODO esto deberia de darmelo un useCase... porque puede que no tenga ciertas categorias...
            BottomBarUI(
                route = MainContainerNavigationRoutes.InformacionGeneralRoute.route,
                title = stringResource(R.string.mainactivity_general),
                unselectedIcon = Icons.Outlined.Person,
                selectedIcon = Icons.Filled.Person
            ),
            BottomBarUI(
                route = MainContainerNavigationRoutes.EstudiosRoute.route,
                title = stringResource(R.string.mainactivity_estudios),
                unselectedIcon = Icons.Outlined.School,
                selectedIcon = Icons.Filled.School
            ),
            BottomBarUI(
                route = MainContainerNavigationRoutes.ExperienciaRoute.route,
                title = stringResource(R.string.mainactivity_experiencia),
                unselectedIcon = Icons.Outlined.Star,
                selectedIcon = Icons.Filled.Star
            ),
            BottomBarUI(
                route = MainContainerNavigationRoutes.MasSobreMiRoute.route,
                title = stringResource(R.string.mainactivity_massobremi),
                unselectedIcon = Icons.Outlined.Add,
                selectedIcon = Icons.Filled.Add
            ),
        )

        // La bottombar en sí
        FloatingBottomBar(
            modifier = Modifier.padding(paddingValues),
        ) {
            bottomBarItems.forEach { item ->
                FloatingBottomBarItem(
                    selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                    unSelectedIcon = {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = item.unselectedIcon,
                            contentDescription = item.title,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = item.selectedIcon,
                            contentDescription = item.title,
                        )
                    },
                    onClick = {
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}