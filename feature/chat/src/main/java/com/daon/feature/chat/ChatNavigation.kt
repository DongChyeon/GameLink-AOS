package com.daon.feature.chat

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val chatRoute = "chat_route"

fun NavController.navigateToChat(navOptions: NavOptions = androidx.navigation.navOptions {  }) {
    navigate(chatRoute, navOptions)
}

fun NavGraphBuilder.chatScreen(
    showSnackBar: (String) -> Unit
) {
    composable(
        route = chatRoute
    ) {
        ChatRoute(
            showSnackBar = showSnackBar
        )
    }
}