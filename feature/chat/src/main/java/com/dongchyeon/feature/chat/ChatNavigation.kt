package com.dongchyeon.feature.chat

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dongchyeon.feature.chat.room.ChatRoomRoute
import com.dongchyeon.feature.chat.room.ChatRoomViewModel
import org.koin.compose.viewmodel.koinViewModel

const val chatRoute = "chat_route"
const val chatRoomRoute = "chat_room_route"

fun NavController.navigateToChat(navOptions: NavOptions = androidx.navigation.navOptions {  }) {
    navigate(chatRoute, navOptions)
}

fun NavController.navigateToChatRoom(
    roomId: String,
    navOptions: NavOptions = androidx.navigation.navOptions {  }
) {
    navigate("$chatRoomRoute?roomId=$roomId", navOptions)
}

fun NavGraphBuilder.chatScreen(
    showSnackBar: (String) -> Unit,
    navigateToChatRoom: (String) -> Unit
) {
    composable(
        route = chatRoute
    ) {
        val chatViewModel: ChatViewModel = koinViewModel<ChatViewModel>()

        ChatRoute(
            chatViewModel = chatViewModel,
            showSnackBar = showSnackBar,
            navigateToChatRoom = navigateToChatRoom
        )
    }

    composable(
        route = "${chatRoomRoute}?roomId={roomId}",
        arguments = listOf(
            navArgument("roomId") {
                type = NavType.StringType
                defaultValue = ""
            }
        )
    ) {
        val chatRoomViewModel: ChatRoomViewModel = koinViewModel<ChatRoomViewModel>()

        ChatRoomRoute(
            chatRoomViewModel = chatRoomViewModel,
            showSnackBar = showSnackBar
        )
    }
}