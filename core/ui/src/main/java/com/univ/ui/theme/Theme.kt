package com.univ.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Yellow90,
    secondary = GreyYellow90,
    tertiary = Navy80,
    surface = DarkYellow20,
    onSurface = Yellow98,
    background = DarkYellow10
)

private val LightColorScheme = lightColorScheme(
    primary = Yellow90,
    secondary = GreyYellow90,
    tertiary = Navy80,
    surface = White100,
    onSurface = DarkYellow10,
    background = GreyYellow95
)

@Composable
fun Dora_ComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}