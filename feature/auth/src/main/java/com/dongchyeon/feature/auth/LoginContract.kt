package com.dongchyeon.feature.auth

import android.content.Context
import com.dongchyeon.common.base.UiEffect
import com.dongchyeon.common.base.UiEvent
import com.dongchyeon.common.base.UiState

class LoginContract {

    data class State(
        val isLoading: Boolean = false
    ) : UiState

    sealed class Event : UiEvent {
        data class KakaoLoginButtonClicked(val context: Context) : Event()
        data object GoogleLoginButtonClicked : Event()
    }

    sealed class Effect : UiEffect {
        data object NavigateToHome: Effect()
        data class ShowSnackBar(val message: String) : Effect()
    }
}