package com.dongchyeon.feature.profile

import androidx.lifecycle.viewModelScope
import com.gamelink.common.base.BaseViewModel
import com.gamelink.repository.ProfileRepository
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val profileRepository: ProfileRepository
) : BaseViewModel<ProfileContract.State, ProfileContract.Event, ProfileContract.Effect>(
    ProfileContract.State()
) {
    init {
        fetchProfile()
    }

    override fun reduceState(event: ProfileContract.Event) {
        when (event) {
            is ProfileContract.Event.RegisterRiotAccount -> {
                postRiotAccount(event.gameName, event.tagLine)
            }

            is ProfileContract.Event.FetchProfile -> {
                fetchProfile()
            }
        }
    }

    private fun fetchProfile() = viewModelScope.launch {
        profileRepository.getProfile()
            .onSuccess {
                val isNotRegistered = it.summonerId == null
                updateState(
                    currentState.copy(
                        isNotRegistered = isNotRegistered,
                        userProfile = it
                    )
                )
            }.onFailure {
                postEffect(ProfileContract.Effect.ShowSnackBar("프로필을 불러오는데 실패했습니다"))
            }
    }

    private fun postRiotAccount(gameName: String, tagLine: String) = viewModelScope.launch {
        profileRepository.postRiotAccount(gameName, tagLine)
            .onSuccess {
                fetchProfile()
            }.onFailure {
                postEffect(ProfileContract.Effect.ShowSnackBar("라이엇 계정을 등록하는데 실패했습니다"))
            }
    }

    fun updateGameNameInput(gameName: String) {
        updateState(currentState.copy(gameNameInput = gameName))
    }

    fun updateTagLineInput(tagLine: String) {
        updateState(currentState.copy(tagLineInput = tagLine))
    }

}