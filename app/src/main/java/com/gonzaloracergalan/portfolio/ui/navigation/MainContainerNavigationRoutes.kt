package com.gonzaloracergalan.portfolio.ui.navigation

sealed class MainContainerNavigationRoutes(val route: String) {
    // basico (el único realmente obligatorio)
    // perfiles
    data object InformacionGeneralRoute :
        MainContainerNavigationRoutes("${MainContainerNavigationRoutes::class.java.simpleName}.${InformacionGeneralRoute::class.java.simpleName}")

    // trabajo y voluntariado
    data object ExperienciaRoute :
        MainContainerNavigationRoutes("${MainContainerNavigationRoutes::class.java.simpleName}.${ExperienciaRoute::class.java.simpleName}")

    // educacion
    data object EstudiosRoute :
        MainContainerNavigationRoutes("${MainContainerNavigationRoutes::class.java.simpleName}.${EstudiosRoute::class.java.simpleName}")

    // premios, certificados y referencias:
    data object PremiosCertificadosRoute :
        MainContainerNavigationRoutes("${MainContainerNavigationRoutes::class.java.simpleName}.${PremiosCertificadosRoute::class.java.simpleName}")

    // publicaciones
    data object PublicacionesRoute :
        MainContainerNavigationRoutes("${MainContainerNavigationRoutes::class.java.simpleName}.${PublicacionesRoute::class.java.simpleName}")

    // proyectos (esta seccion llevará a github siempre)
    data object ProyectosRoute :
        MainContainerNavigationRoutes("${MainContainerNavigationRoutes::class.java.simpleName}.${ProyectosRoute::class.java.simpleName}")

    // habilidades, idiomas e intereses
    data object MasSobreMiRoute :
        MainContainerNavigationRoutes("${MainContainerNavigationRoutes::class.java.simpleName}.${MasSobreMiRoute::class.java.simpleName}")
}