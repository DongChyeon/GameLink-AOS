package com.dongchyeon.feature.profile

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val profileViewModelModule = module {
    viewModel<ProfileViewModel> { ProfileViewModel(get()) }
}