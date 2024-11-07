package com.gamelink.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gamelink.designsystem.theme.GameLinkTheme

@Composable
internal fun HomeRoute(
    showSnackBar: (String) -> Unit
) {
   HomeScreen {

   }
}

@Composable
@Preview
internal fun HomeRoutePreview() {
    GameLinkTheme {
        HomeRoute { }
    }
}

@Composable
internal fun HomeScreen(
    showSnackbar: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GameLinkTheme.colors.background)
    ) {

    }
}