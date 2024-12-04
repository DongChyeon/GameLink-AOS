package com.dongchyeon.feature.chat.room

import com.dongchyeon.common.base.UiEffect
import com.dongchyeon.common.base.UiEvent
import com.dongchyeon.common.base.UiState
import com.dongchyeon.model.response.ChatMessageResponse

class ChatRoomContract {
    data class State(
        val isLoading: Boolean = false,
        val messages: List<ChatMessageResponse> = emptyList(),
        val content: String = ""
    ) : UiState

    sealed class Event : UiEvent {
        data class SendMessage(val message: String) : Event()
    }

    sealed class Effect : UiEffect {
        data class ShowSnackBar(val message: String) : Effect()
        data object ScrollToBottom : Effect()
    }
}