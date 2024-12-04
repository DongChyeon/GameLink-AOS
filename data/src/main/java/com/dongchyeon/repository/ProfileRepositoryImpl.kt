package com.dongchyeon.repository

import com.dongchyeon.datasource.ProfileDataSource
import com.dongchyeon.model.response.UserProfileResponse

class ProfileRepositoryImpl(
    private val profileDataSource: ProfileDataSource
) : ProfileRepository {
    override suspend fun getProfile(): Result<UserProfileResponse> =
        profileDataSource.getProfile()

    override suspend fun postRiotAccount(gameName: String, tagLine: String): Result<Unit> =
        profileDataSource.postRiotAccount(gameName, tagLine)
}