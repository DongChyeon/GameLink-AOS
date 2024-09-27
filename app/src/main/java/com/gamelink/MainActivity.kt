package com.gamelink

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.gamelink.common.rememberGameLinkAppState
import com.gamelink.common.rememberGameLinkBottomSheetState
import com.gamelink.ui.theme.GameLinkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameLinkTheme {
                GameLinkNavHost(
                    appState = rememberGameLinkAppState(),
                    bottomSheetState = rememberGameLinkBottomSheetState()
                )
            }
        }
    }
}

