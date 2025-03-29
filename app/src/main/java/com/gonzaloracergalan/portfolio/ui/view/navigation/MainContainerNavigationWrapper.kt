package com.gonzaloracergalan.portfolio.ui.view.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gonzaloracergalan.portfolio.ui.view.screen.EstudiosScreen
import com.gonzaloracergalan.portfolio.ui.view.screen.ExperienciaScreen
import com.gonzaloracergalan.portfolio.ui.view.screen.InformacionGeneralScreen
import com.gonzaloracergalan.portfolio.ui.view.screen.MasSobreMiScreen
import com.gonzaloracergalan.portfolio.ui.view.screen.PremiosCertificadosScreen
import com.gonzaloracergalan.portfolio.ui.view.screen.ProyectosScreen
import com.gonzaloracergalan.portfolio.ui.view.screen.PublicacionesScreen

/**
 * Navegación de las pantallas que componen la pantalla principal
 */
@Composable
fun MainContainerNavigationWrapper(
    navController: NavHostController,
    paddingValues: PaddingValues,
) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = MainContainerNavigationRoutes.InformacionGeneralRoute.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {
        composable(MainContainerNavigationRoutes.InformacionGeneralRoute.route) {
            InformacionGeneralScreen(paddingValues)
        }
        composable(MainContainerNavigationRoutes.EstudiosRoute.route) {
            EstudiosScreen(paddingValues)
        }
        composable(MainContainerNavigationRoutes.ExperienciaRoute.route) {
            ExperienciaScreen(paddingValues)
        }
        composable(MainContainerNavigationRoutes.PremiosCertificadosRoute.route) {
            PremiosCertificadosScreen(paddingValues)
        }
        composable(MainContainerNavigationRoutes.PublicacionesRoute.route) {
            PublicacionesScreen(paddingValues)
        }
        composable(MainContainerNavigationRoutes.ProyectosRoute.route) {
            ProyectosScreen(paddingValues)
        }
        composable(MainContainerNavigationRoutes.MasSobreMiRoute.route) {
            MasSobreMiScreen(paddingValues)
        }
    }
}