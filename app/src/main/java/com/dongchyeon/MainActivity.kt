package com.dongchyeon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.dongchyeon.ui.rememberGameLinkAppState
import com.dongchyeon.ui.rememberGameLinkBottomSheetState
import com.dongchyeon.ui.theme.GameLinkTheme

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

