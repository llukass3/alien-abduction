package com.example.alien_abduction.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView

private val AlienColorScheme = darkColorScheme(
    primary = AlienWhite,
    secondary = AlienGreen,
    background = AlienBlack,
    surface = AlienWhite,
    onPrimary = AlienBlack,
    onSecondary = AlienWhite,
    onBackground = AlienWhite,
    onSurface = AlienBlack,
)

@Composable
fun AlienabductionTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = AlienColorScheme,
        typography = Typography,
        content = content
    )
}
