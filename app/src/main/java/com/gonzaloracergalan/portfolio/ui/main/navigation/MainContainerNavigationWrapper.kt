package com.gonzaloracergalan.portfolio.ui.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gonzaloracergalan.gymapp.ui.main.core.navigation.bottom.MainContainerBottomNavigationWrapper
import com.gonzaloracergalan.gymapp.ui.main.valorespordefecto.ValoresPorDefectoScreen
import com.gonzaloracergalan.portfolio.ui.navigation.MainContainerDrawerNavigationRoutes

/**
 * Navegación de las pantallas que componen la pantalla principal
 */
@Composable
fun MainContainerNavigationWrapper(
    mainContainerNavController: NavHostController,
) {
    NavHost(
        navController = mainContainerNavController,
        startDestination = MainContainerNavigationRoutes.InformacionGeneralRoute.route
    ) {
        composable(MainContainerNavigationRoutes.InformacionGeneralRoute.route) {
            crear screens basicas..
        }
        composable(MainContainerNavigationRoutes.EstudiosRoute.route) {

        }
        composable(MainContainerNavigationRoutes.ExperienciaRoute.route) {

        }
        composable(MainContainerNavigationRoutes.MasSobreMiRoute.route) {

        }
    }
}