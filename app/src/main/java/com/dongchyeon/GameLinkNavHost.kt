package com.dongchyeon

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import com.dongchyeon.feature.chat.chatScreen
import com.dongchyeon.feature.profile.profileScreen
import com.dongchyeon.designsystem.component.BottomNavigationBar
import com.dongchyeon.designsystem.component.BottomNavigationBarItem
import com.dongchyeon.designsystem.theme.GameLinkTheme
import com.dongchyeon.feature.auth.loginRoute
import com.dongchyeon.feature.auth.loginScreen
import com.dongchyeon.feature.chat.navigateToChatRoom
import com.dongchyeon.ui.GameLinkAppState
import com.dongchyeon.ui.GameLinkBottomSheetState
import com.gamelink.TopLevelDestination
import homeScreen
import navigateToHome

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
            backgroundColor = GameLinkTheme.colors.background,
            snackbarHost = {
                appState.scaffoldState.snackbarHostState
            },
            bottomBar = {
                AnimatedVisibility(
                    appState.shouldShowBottomBar,
                    enter = slideInVertically { it },
                    exit = slideOutVertically { it },
                ) {
                    GameLinkBottomNavigationBar(
                        destinations = appState.topLevelDestinations,
                        currentDestination = appState.currentDestination,
                        onNavigateToDestination = { destination ->
                            Log.d("GameLinkNavHost", "destination: $destination")
                            appState.navigateToTopLevelDestination(destination)
                        }
                    )
                }
            }
        ) { padding ->
            NavHost(
                modifier = Modifier.padding(padding),
                navController = navController,
                startDestination = loginRoute
            ) {
                loginScreen(
                    showSnackBar = appState::showSnackbar,
                    navigateToHome = navController::navigateToHome
                )
                homeScreen(
                    showSnackBar = appState::showSnackbar
                )
                chatScreen(
                    showSnackBar = appState::showSnackbar,
                    navigateToChatRoom = navController::navigateToChatRoom
                )
                profileScreen(
                    showSnackBar = appState::showSnackbar
                )
            }
        }
    }
}

@Composable
private fun GameLinkBottomNavigationBar(
    modifier: Modifier = Modifier,
    destinations: List<TopLevelDestination>,
    currentDestination: NavDestination?,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    backgroundColor: Color = Color(0xFF1E1E1E),
    selectedContentColor: Color = GameLinkTheme.colors.primary3,
    unselectedContentColor: Color = GameLinkTheme.colors.gray1
) {
    BottomNavigationBar(
        modifier = modifier,
        backgroundColor = backgroundColor
    ) {
        destinations.forEachIndexed { index, destination ->
            if (index == 2) {
                // 중간에 Spacer 추가
                Spacer(modifier = Modifier.width(55.dp))
            }

            val isSelected = currentDestination.isTopLevelDestinationInHierarchy(destination)

            BottomNavigationBarItem(
                label = destination.label,
                selected = isSelected,
                icon = destination.icon,
                selectedContentColor = selectedContentColor,
                unselectedContentColor = unselectedContentColor,
                onClick = {
                    if (!isSelected) onNavigateToDestination(destination)
                }
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false