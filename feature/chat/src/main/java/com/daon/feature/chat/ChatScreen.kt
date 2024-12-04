package com.daon.feature.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gamelink.designsystem.theme.GameLinkTheme

@Composable
internal fun ChatRoute(
    showSnackBar: (String) -> Unit
) {

}

@Composable
internal fun ChatScreen(
    showSnackBar: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GameLinkTheme.colors.background)
    ) {

    }
}