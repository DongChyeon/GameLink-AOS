package com.dongchyeon.feature.profile

import com.gamelink.common.base.UiEffect
import com.gamelink.common.base.UiEvent
import com.gamelink.common.base.UiState

class ProfileContract {

    data class State(
        val isLoading: Boolean = false,
        val isNotRegistered: Boolean = false
    ) : UiState

    sealed class Event : UiEvent { }

    sealed class Effect : UiEffect {
        data class ShowSnackBar(val message: String) : Effect()
    }
}