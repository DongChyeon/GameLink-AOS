package com.gamelink

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import com.gamelink.common.GameLinkAppState
import com.gamelink.common.GameLinkBottomSheetState
import com.gamelink.feature.auth.loginRoute
import com.gamelink.feature.auth.loginScreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GameLinkNavHost(
    appState: GameLinkAppState,
    bottomSheetState: GameLinkBottomSheetState
) {
    val navController = appState.navController

    ModalBottomSheetLayout(
        sheetContent = {
            bottomSheetState.bottomSheetContent.value?.invoke(this)
        },
        sheetState = bottomSheetState.bottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp),
        modifier = Modifier.navigationBarsPadding()
    ) {
        Scaffold(
            backgroundColor = Color.White,
            snackbarHost = {
                appState.scaffoldState.snackbarHostState
            }
        ) { padding ->
            NavHost(
                modifier = Modifier.padding(padding),
                navController = navController,
                startDestination = loginRoute
            ) {
                loginScreen(
                    showSnackBar = appState::showSnackbar,
                    navigateToHome = {
                        navController.navigate("home")
                    }
                )
            }
        }
    }
}