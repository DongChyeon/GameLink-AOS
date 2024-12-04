package com.dongchyeon.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dongchyeon.designsystem.theme.GameLinkTheme

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
    showSnackBar: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GameLinkTheme.colors.background)
    ) {

    }
}