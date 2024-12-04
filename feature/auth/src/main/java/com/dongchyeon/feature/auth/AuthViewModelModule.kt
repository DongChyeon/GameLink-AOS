package com.dongchyeon.feature.auth

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authViewModelModule = module {
    viewModel<LoginViewModel> { LoginViewModel(get(), get()) }
}