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

    }

    private fun fetchProfile() = viewModelScope.launch {
        profileRepository.getProfile()
            .onSuccess {

            }.onFailure {
                postEffect(ProfileContract.Effect.ShowSnackBar("프로필을 불러오는데 실패했습니다"))
            }
    }

}