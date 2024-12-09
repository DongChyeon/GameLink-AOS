package com.dongchyeon.feature.chat

import androidx.lifecycle.viewModelScope
import com.dongchyeon.common.base.BaseViewModel
import com.dongchyeon.repository.ChatRepository
import kotlinx.coroutines.launch

class ChatViewModel(
    private val chatRepository: ChatRepository
) : BaseViewModel<ChatContract.State, ChatContract.Event, ChatContract.Effect>(
    ChatContract.State()
) {
    init {
        loadMoreChatRooms()
    }

    override fun reduceState(event: ChatContract.Event) {
        when (event) {
            is ChatContract.Event.RefreshChatRooms -> {
                getChatRooms()
            }
            is ChatContract.Event.LoadMoreChatRooms -> {
                loadMoreChatRooms()
            }
            is ChatContract.Event.SelectChatRoom -> {
                postEffect(ChatContract.Effect.NavigateToChatRoom(event.roomId))
            }
        }
    }

    private fun getChatRooms() = viewModelScope.launch {
        updateState(
            currentState.copy(
                page = 1,
                isFetchingChatRooms = true
            )
        )

        chatRepository.getMyChatRooms(
            page = currentState.page,
            size = currentState.pageSize,
            sort = null
        ).onSuccess {
            updateState(currentState.copy(
                myChatRooms = it.content,
                page = currentState.page + 1,
                isLastPage = it.last,
                isFetchingChatRooms = false
            ))
        }.onFailure {
            updateState(currentState.copy(isFetchingChatRooms = false))
            postEffect(ChatContract.Effect.ShowSnackBar("채팅방을 불러오는데 실패했습니다"))
        }

        /*
        chatRepository.getChatRooms(
            page = currentState.page,
            size = currentState.pageSize,
            position = null,
            gameType = null,
            rankTiers = null,
            sort = null
        ).onSuccess {
            updateState(currentState.copy(
                chatRooms = it.content,
                page = currentState.page + 1,
                isLastPage = it.last,
                isFetchingChatRooms = false
            ))
        }.onFailure {
            updateState(currentState.copy(isFetchingChatRooms = false))
            postEffect(ChatContract.Effect.ShowSnackBar("채팅방을 불러오는데 실패했습니다"))
        }*/
    }

    private fun loadMoreChatRooms() = viewModelScope.launch {
        if (currentState.isFetchingChatRooms || currentState.isLastPage) return@launch

        updateState(currentState.copy(isFetchingChatRooms = true))

        chatRepository.getMyChatRooms(
            page = currentState.page,
            size = currentState.pageSize,
            sort = null
        ).onSuccess {
            updateState(currentState.copy(
                myChatRooms = currentState.myChatRooms + it.content,
                page = currentState.page + 1,
                isLastPage = it.last,
                isFetchingChatRooms = false
            ))
        }.onFailure {
            updateState(currentState.copy(isFetchingChatRooms = false))
            postEffect(ChatContract.Effect.ShowSnackBar("채팅방을 불러오는데 실패했습니다"))
        }

        /*chatRepository.getChatRooms(
            position = null,
            gameType = null,
            rankTiers = null,
            page = currentState.page,
            size = currentState.pageSize,
            sort = null
        ).onSuccess {
            updateState(currentState.copy(
                chatRooms = currentState.chatRooms + it.content,
                isFetchingChatRooms = false
            ))
        }.onFailure {
            updateState(currentState.copy(isFetchingChatRooms = false))
            postEffect(ChatContract.Effect.ShowSnackBar("채팅방을 불러오는데 실패했습니다"))
        }*/
    }
}