package com.gonzaloracergalan.portfolio.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColorPalette = lightColorScheme(
    primary = GreenPrimary,
    secondary = GreenSecondary,
    background = SoftBackground,
    surface = SoftSurface,
    error = ElegantError,
    onPrimary = SoftOnPrimary,
    onSecondary = DarkText,
    onBackground = DarkText,
    onSurface = DarkText,
    onError = SoftOnPrimary,
)

private val DarkColorPalette = darkColorScheme(
    primary = GreenPrimary,
    secondary = GreenSecondary,
    background = DarkBackground,
    surface = DarkSurface,
    error = ElegantError,
    onPrimary = SoftOnPrimary,
    onSecondary = LightTextDark,
    onBackground = LightTextDark,
    onSurface = LightTextDark,
    onError = SoftOnPrimary,
)
@Composable
fun PortfolioTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorPalette
        else -> LightColorPalette
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}