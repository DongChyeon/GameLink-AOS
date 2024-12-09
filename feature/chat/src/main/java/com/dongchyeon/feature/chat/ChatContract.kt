package com.dongchyeon.feature.chat

import com.dongchyeon.common.base.UiEffect
import com.dongchyeon.common.base.UiEvent
import com.dongchyeon.common.base.UiState
import com.dongchyeon.model.response.ChatRoomResponse
import com.dongchyeon.model.response.MyChatRoomListResponse
import com.dongchyeon.model.response.MyChatRoomResponse

class ChatContract {

    data class State(
        val isLoading: Boolean = false,
        val myChatRooms: List<MyChatRoomResponse> = emptyList(),
        val chatRooms: List<ChatRoomResponse> = emptyList(),
        val page: Int = 0,
        val pageSize: Int = 10,
        val isLastPage: Boolean = false,
        val isFetchingChatRooms: Boolean = false
    ) : UiState

    sealed class Event : UiEvent {
        data object RefreshChatRooms : Event()
        data object LoadMoreChatRooms : Event()
        data class SelectChatRoom(val roomId: String) : Event()
    }

    sealed class Effect : UiEffect {
        data class ShowSnackBar(val message: String) : Effect()
        data class NavigateToChatRoom(val roomId: String) : Effect()
    }
}