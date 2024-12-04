package com.dongchyeon.feature.chat.room

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.dongchyeon.common.base.BaseViewModel
import com.dongchyeon.repository.ChatRepository
import kotlinx.coroutines.launch

class ChatRoomViewModel(
    private val chatRepository: ChatRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<ChatRoomContract.State, ChatRoomContract.Event, ChatRoomContract.Effect>(
    ChatRoomContract.State()
) {
    private val roomId: String = savedStateHandle.get<String>("roomId") ?: ""

    init {
        loadMessages()
    }


    override fun reduceState(event: ChatRoomContract.Event) {
        when (event) {
            is ChatRoomContract.Event.SendMessage -> {
                //sendMessage(event.message)
            }
        }
    }

    private fun loadMessages() = viewModelScope.launch {
        chatRepository.getChatRoomMessages(
            roomId = roomId,
            page = 1,
            size = 20,
            sort = null
        ).onSuccess {
            updateState(currentState.copy(messages = it.content))
            postEffect(ChatRoomContract.Effect.ScrollToBottom)
        }.onFailure {
            postEffect(ChatRoomContract.Effect.ShowSnackBar("메시지를 불러오는데 실패했습니다"))
        }
    }

    fun updateInputContent(content: String) {
        updateState(currentState.copy(content = content))
    }
}