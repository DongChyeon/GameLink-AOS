package com.daon.feature.chat

import com.gamelink.common.base.UiEffect
import com.gamelink.common.base.UiEvent
import com.gamelink.common.base.UiState
import com.gamelink.model.response.ChatRoomResponse

class ChatContract {

    data class State(
        val isLoading: Boolean = false,
        val chatRooms: List<ChatRoomResponse> = emptyList(),
        val page: Int = 0,
        val pageSize: Int = 10,
        val isLastPage: Boolean = false,
        val isFetchingChatRooms: Boolean = false
    ) : UiState

    sealed class Event : UiEvent {
        data object RefreshChatRooms : Event()
        data object LoadMoreChatRooms : Event()
    }

    sealed class Effect : UiEffect {
        data class ShowSnackBar(val message: String) : Effect()
    }
}