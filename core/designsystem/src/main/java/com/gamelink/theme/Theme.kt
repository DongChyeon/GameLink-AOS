package com.gamelink.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

fun gameLinkColors() = GameLinkColors()
fun gameLinkTypography() = GameLinkTypography()

@Composable
fun GameLinkTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colors: GameLinkColors = gameLinkColors(),
    darkColors: GameLinkColors = gameLinkColors(),
    typography: GameLinkTypography = gameLinkTypography(),
    content: @Composable () -> Unit
) {
    val currentColor = if (darkTheme) darkColors else colors
    val rememberedColors = currentColor.copy().apply { updateColorFrom(currentColor) }
    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalTypography provides typography
    ) {
        ProvideTextStyle(typography.body2, content = content)
    }

    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }
}