package com.gonzaloracergalan.portfolio.ui.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.EmojiEvents
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.Work
import androidx.compose.ui.graphics.vector.ImageVector
import com.gonzaloracergalan.portfolio.R
import com.gonzaloracergalan.portfolio.ui.navigation.MainContainerNavigationRoutes

data class MainActivityModel(
    val sections : Set<Section>
) {
    enum class Section(
        val unselectedIcon: ImageVector,
        val selectedIcon: ImageVector,
        @StringRes
        val text: Int,
        val route: String
    ) {
        INFORMACION_GENERAL(
            unselectedIcon = Icons.Outlined.Person,
            selectedIcon = Icons.Default.Person,
            text = R.string.mainactivity_hamburguerinfogeneral,
            route = MainContainerNavigationRoutes.InformacionGeneralRoute.route
        ),
        EXPERIENCIA(
            unselectedIcon = Icons.Outlined.Work,
            selectedIcon = Icons.Default.Work,
            text = R.string.mainactivity_hamburguerexperiencia,
            route = MainContainerNavigationRoutes.ExperienciaRoute.route
        ),
        ESTUDIOS(
            unselectedIcon = Icons.Outlined.School,
            selectedIcon = Icons.Default.School,
            text = R.string.mainactivity_hamburguerestudios,
            route = MainContainerNavigationRoutes.EstudiosRoute.route
        ),
        PREMIOS_CERTIFICADOS(
            unselectedIcon = Icons.Outlined.EmojiEvents,
            selectedIcon = Icons.Default.EmojiEvents,
            text = R.string.mainactivity_hamburguerpremio,
            route = MainContainerNavigationRoutes.PremiosCertificadosRoute.route
        ),
        PUBLICACIONES(
            unselectedIcon = Icons.AutoMirrored.Outlined.MenuBook,
            selectedIcon = Icons.AutoMirrored.Default.MenuBook,
            text = R.string.mainactivity_hamburguerpublicaciones,
            route = MainContainerNavigationRoutes.PublicacionesRoute.route
        ),
        PROYECTOS(
            unselectedIcon = Icons.Outlined.Build,
            selectedIcon = Icons.Default.Build,
            text = R.string.mainactivity_hamburguerproyectos,
            route = MainContainerNavigationRoutes.ProyectosRoute.route
        ),
        MAS_SOBRE_MI(
            unselectedIcon =  Icons.Outlined.Info,
            selectedIcon =  Icons.Default.Info,
            text = R.string.mainactivity_hamburguermas,
            route = MainContainerNavigationRoutes.MasSobreMiRoute.route
        )
    }
}