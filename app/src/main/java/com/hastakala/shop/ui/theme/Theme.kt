package com.hastakala.shop.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = PurplePrimary,
    onPrimary = OnPurplePrimary,
    primaryContainer = PurpleSecondary.copy(alpha = 0.35f),
    secondary = PurpleSecondary,
    onSecondary = Color(0xFF1A0E2E),
    tertiary = PurpleSecondary,
    background = Color(0xFFFDF7FF),
    surface = Color(0xFFFDF7FF),
    surfaceVariant = PurpleSurface,
    onSurface = Color(0xFF1C1B1F),
    onSurfaceVariant = Color(0xFF49454F),
)

private val DarkColors = darkColorScheme(
    primary = PurpleSecondary,
    onPrimary = Color(0xFF1A0E2E),
    primaryContainer = PurplePrimary,
    secondary = PurpleSecondary,
    onSecondary = Color(0xFF1A0E2E),
    tertiary = PurpleSecondary,
    background = Color(0xFF121018),
    surface = Color(0xFF121018),
    surfaceVariant = Color(0xFF2D2640),
    onSurface = Color(0xFFE6E1E5),
    onSurfaceVariant = Color(0xFFCAC4D0),
)

@Composable
fun HastaKalaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = Typography,
        content = content,
    )
}
