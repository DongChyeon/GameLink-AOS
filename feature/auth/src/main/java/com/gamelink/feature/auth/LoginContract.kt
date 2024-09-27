package com.gamelink.feature.auth

import android.content.Context
import com.gamelink.common.base.UiEffect
import com.gamelink.common.base.UiEvent
import com.gamelink.common.base.UiState

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