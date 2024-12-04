package com.dongchyeon.feature.profile

import com.dongchyeon.common.base.UiEffect
import com.dongchyeon.common.base.UiEvent
import com.dongchyeon.common.base.UiState
import com.dongchyeon.model.response.UserProfileResponse

class ProfileContract {

    data class State(
        val isLoading: Boolean = false,
        val isNotRegistered: Boolean = false,

        val userProfile: UserProfileResponse? = null,

        val gameNameInput: String = "",
        val tagLineInput: String = ""
    ) : UiState

    sealed class Event : UiEvent {
        data object FetchProfile : Event()
        data class RegisterRiotAccount(
            val gameName: String,
            val tagLine: String
        ) : Event()
    }

    sealed class Effect : UiEffect {
        data class ShowSnackBar(val message: String) : Effect()
    }
}