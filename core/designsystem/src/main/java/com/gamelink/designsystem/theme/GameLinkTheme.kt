package com.gamelink.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

object GameLinkTheme {
    val colors: GameLinkColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: GameLinkTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}