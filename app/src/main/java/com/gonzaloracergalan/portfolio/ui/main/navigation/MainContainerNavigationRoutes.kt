package com.gonzaloracergalan.portfolio.ui.main.navigation

sealed class MainContainerNavigationRoutes(val route: String) {
    data object InformacionGeneralRoute : MainContainerNavigationRoutes("${MainContainerNavigationRoutes::class.java.simpleName}.${InformacionGeneralRoute::class.java.simpleName}")
    data object ExperienciaRoute : MainContainerNavigationRoutes("${MainContainerNavigationRoutes::class.java.simpleName}.${ExperienciaRoute::class.java.simpleName}")
    data object EstudiosRoute : MainContainerNavigationRoutes("${MainContainerNavigationRoutes::class.java.simpleName}.${EstudiosRoute::class.java.simpleName}")
    data object MasSobreMiRoute : MainContainerNavigationRoutes("${MainContainerNavigationRoutes::class.java.simpleName}.${MasSobreMiRoute::class.java.simpleName}")
}