package com.dongchyeon.feature.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.koin.compose.viewmodel.koinViewModel

const val profileRoute = "profile_route"

fun NavController.navigateToProfile(navOptions: NavOptions = androidx.navigation.navOptions {  }) {
    navigate(profileRoute, navOptions)
}

fun NavGraphBuilder.profileScreen(
    showSnackBar: (String) -> Unit
) {
    composable(
        route = profileRoute
    ) {
        val profileViewModel: ProfileViewModel = koinViewModel<ProfileViewModel>()

        ProfileRoute(
            showSnackBar = showSnackBar,
            profileViewModel = profileViewModel
        )
    }
}