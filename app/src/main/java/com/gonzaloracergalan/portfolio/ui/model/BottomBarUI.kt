package com.gonzaloracergalan.portfolio.ui.model

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomBarUI(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)