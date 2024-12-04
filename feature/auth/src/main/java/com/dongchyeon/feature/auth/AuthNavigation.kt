package com.dongchyeon.feature.auth

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import org.koin.compose.viewmodel.koinViewModel

const val loginRoute = "login_route"

fun NavController.navigateToLogin() {
    navigate(loginRoute, navOptions {
        popUpTo(loginRoute) {
            inclusive = true
        }
    })
}

fun NavGraphBuilder.loginScreen(showSnackBar : (String) -> Unit, navigateToHome : () -> Unit) {
    composable(
        route = loginRoute
    ) {
        val loginViewModel: LoginViewModel = koinViewModel<LoginViewModel>()

        LoginRoute(
            showSnackBar = showSnackBar,
            navigateToHome = navigateToHome,
            loginViewModel = loginViewModel
        )
    }
}