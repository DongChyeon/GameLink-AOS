package com.dongchyeon.feature.chat

import androidx.lifecycle.SavedStateHandle
import com.dongchyeon.feature.chat.room.ChatRoomViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val chatViewModelModule = module {
    viewModel<ChatViewModel> { ChatViewModel(get()) }
    viewModel<ChatRoomViewModel> { (savedStateHandle: SavedStateHandle) ->
        ChatRoomViewModel(get(), savedStateHandle)
    }
}