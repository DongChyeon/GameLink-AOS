package com.dongchyeon.feature.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.gamelink.designsystem.theme.GameLinkTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun ProfileRoute(
    showSnackBar: (String) -> Unit,
    profileViewModel: ProfileViewModel
) {
    LaunchedEffect(Unit) {
        profileViewModel.effect.collectLatest { event ->
            when (event) {
                is ProfileContract.Effect.ShowSnackBar -> {
                    showSnackBar(event.message)
                }
            }
        }
    }

    ProfileScreen(
        profileViewModel = profileViewModel
    )
}

@Composable
internal fun ProfileScreen(
    profileViewModel: ProfileViewModel,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GameLinkTheme.colors.background)
    ) {

    }
}