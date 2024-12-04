package com.daon.feature.chat

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val chatViewModelModule = module {
    viewModel<ChatViewModel> { ChatViewModel(get()) }
}