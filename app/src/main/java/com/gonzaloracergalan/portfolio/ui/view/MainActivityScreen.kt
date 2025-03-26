package com.gonzaloracergalan.portfolio.ui.view

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.gonzaloracergalan.portfolio.ui.model.MainActivityModel
import com.gonzaloracergalan.portfolio.ui.navigation.MainContainerNavigationRoutes
import com.gonzaloracergalan.portfolio.ui.navigation.MainContainerNavigationWrapper
import com.gonzaloracergalan.portfolio.ui.state.MainActivityState
import com.gonzaloracergalan.portfolio.ui.theme.PortfolioTheme
import com.gonzaloracergalan.portfolio.ui.view.component.TransparentCircledTopBar
import com.gonzaloracergalan.portfolio.ui.viewmodel.MainActivityViewModel

@Composable
fun MainActivityScreen(paddingValues: PaddingValues) {
    val mainActivityViewModel: MainActivityViewModel = viewModel()
    val activeSectionsState by mainActivityViewModel.mainActivityState.collectAsStateWithLifecycle()

    // todo mejorar y tratar todos los casos... quizas mini navegacion no estaria mal
    // todo si no contengo basico no pinto nada de nada
    // todo quizas este state no requiera de tantos casos, solo loading si y no
    if (activeSectionsState is MainActivityState.Idle) {
        val sections = (activeSectionsState as MainActivityState.Idle).data.sections
        if (sections.contains(MainActivityModel.Section.INFORMACION_GENERAL)) {
            SuccessMainActivityScreen(paddingValues, sections)
        } else {
            NoDataMainActivityScreen()
        }
    } else {
        NoDataMainActivityScreen()
    }
}

/**
 * Si no tengo datos correctos me limito a pintar un mensaje feo todo dejar bonito
 */
@Composable
private fun NoDataMainActivityScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("No data or error loading data")
    }
}

/**
 * En caso de que tenga datos cargados para mostrar la pantalla principal
 */
@Composable
private fun SuccessMainActivityScreen(
    paddingValues: PaddingValues,
    sections: Set<MainActivityModel.Section>
) {
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
            paddingValues = paddingValues,
            navController = navHostController,
            sections = sections,
            onOptionSelected = {
                isDrawerOpen = false
            }
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
    paddingValues: PaddingValues,
    navController: NavHostController,
    sections: Set<MainActivityModel.Section>,
    onOptionSelected: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingValues)
    ) {
        Text(
            text = "Secciones",
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 20.sp
        )

        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
        )

        Spacer(Modifier.height(8.dp))

        // todo hay que hacer mas bonito esto por dios
        // todo que no me deje navegar a la pantalla en la que estoy y tengo que controlar el popback
        // basico (el único realmente obligatorio)
        // perfiles
        if (sections.contains(MainActivityModel.Section.INFORMACION_GENERAL)) {
            Button({
                navController.navigate(MainContainerNavigationRoutes.InformacionGeneralRoute.route)
                onOptionSelected()
            }) {
                Text("Información general")
            }
        }

        // trabajo y voluntariado
        if (sections.contains(MainActivityModel.Section.EXPERIENCIA)) {
            Button({
                navController.navigate(MainContainerNavigationRoutes.ExperienciaRoute.route)
                onOptionSelected()
            }) {
                Text("Experiencia")
            }
        }

        // educacion
        if (sections.contains(MainActivityModel.Section.ESTUDIOS)) {
            Button({
                navController.navigate(MainContainerNavigationRoutes.EstudiosRoute.route)
                onOptionSelected()
            }) {
                Text("Estudios")
            }
        }

        // premios, certificados y referencias:
        if (sections.contains(MainActivityModel.Section.PREMIOS_CERTIFICADOS)) {
            Button({
                navController.navigate(MainContainerNavigationRoutes.PremiosCertificadosRoute.route)
                onOptionSelected()
            }) {
                Text("Premios y certificados")
            }
        }

        // publicaciones
        if (sections.contains(MainActivityModel.Section.PUBLICACIONES)) {
            Button({
                navController.navigate(MainContainerNavigationRoutes.PublicacionesRoute.route)
                onOptionSelected()
            }) {
                Text("Publicaciones")
            }
        }

        // proyectos (esta seccion llevará a github siempre)
        if (sections.contains(MainActivityModel.Section.PROYECTOS)) {
            Button({
                navController.navigate(MainContainerNavigationRoutes.ProyectosRoute.route)
                onOptionSelected()
            }) {
                Text("Proyectos")
            }
        }

        // habilidades, idiomas e intereses
        if (sections.contains(MainActivityModel.Section.MAS_SOBRE_MI)) {
            Button({
                navController.navigate(MainContainerNavigationRoutes.MasSobreMiRoute.route)
                onOptionSelected()
            }) {
                Text("Más sobre mi")
            }
        }
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

@Composable
@Preview
private fun MainActivityScreenPreview() {
    PortfolioTheme {
        HamburguerMenuOptions(
            paddingValues = PaddingValues(vertical = 16.dp),
            navController = rememberNavController(),
            sections = setOf(
                MainActivityModel.Section.INFORMACION_GENERAL,
                MainActivityModel.Section.EXPERIENCIA,
                MainActivityModel.Section.ESTUDIOS,
                MainActivityModel.Section.PREMIOS_CERTIFICADOS,
                MainActivityModel.Section.PUBLICACIONES,
                MainActivityModel.Section.PROYECTOS,
                MainActivityModel.Section.MAS_SOBRE_MI
            )
        ) {

        }
    }
}