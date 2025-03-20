package com.gonzaloracergalan.portfolio.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gonzaloracergalan.portfolio.ui.view.EstudiosScreen
import com.gonzaloracergalan.portfolio.ui.view.ExperienciaScreen
import com.gonzaloracergalan.portfolio.ui.view.InformacionGeneralScreen
import com.gonzaloracergalan.portfolio.ui.view.MasSobreMiScreen

/**
 * Navegación de las pantallas que componen la pantalla principal
 */
@Composable
fun MainContainerNavigationWrapper(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = MainContainerNavigationRoutes.InformacionGeneralRoute.route,
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
        composable(MainContainerNavigationRoutes.MasSobreMiRoute.route) {
            MasSobreMiScreen(paddingValues)
        }
    }
}