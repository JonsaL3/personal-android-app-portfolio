package com.gonzaloracergalan.portfolio.ui.view

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
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
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gonzaloracergalan.portfolio.ui.model.MainActivityModel
import com.gonzaloracergalan.portfolio.ui.navigation.MainContainerNavigationWrapper
import com.gonzaloracergalan.portfolio.ui.state.MainActivityState
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
    val drawerWidth = screenWidth * 0.3f
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

    // Contenedor principal menu hamburguesa + vista principal con su navegación
    Box(Modifier.fillMaxSize()) {
        // Menu lateral
        HamburguerMenuOptions(
            modifier = Modifier.width(drawerWidth),
            navHostController = navHostController,
            paddingValues = paddingValues,
            sections = sections,
            onOptionSelected = { isDrawerOpen = false }
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
                },
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
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    sections: Set<MainActivityModel.Section>,
    onOptionSelected: () -> Unit
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.primary)
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        sections.forEach { item ->
            HamburguerMenuSection(
                modifier = modifier.weight(1f),
                section = item,
                isSelected = currentRoute == item.route,
                onClick = {
                    navHostController.navigate(item.route) {
                        navHostController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                    if (currentRoute != item.route) {
                        onOptionSelected()
                    }
                }
            )
        }
    }
}

@Composable
private fun HamburguerMenuSection(
    modifier: Modifier,
    section: MainActivityModel.Section,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.clickable(onClick = onClick).background(
            if (isSelected)
                MaterialTheme.colorScheme.onBackground
            else
                MaterialTheme.colorScheme.primary
        ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = if (isSelected) section.selectedIcon else section.unselectedIcon,
            contentDescription = stringResource(section.text),
            tint = if (isSelected)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.onBackground
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = stringResource(section.text),
            color = if (isSelected)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.onBackground,
            modifier = Modifier,
            textAlign = TextAlign.Center,
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