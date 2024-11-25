package com.gamelink.repository

import com.gamelink.datasource.ProfileDataSource
import com.gamelink.model.response.UserProfileResponse

class ProfileRepositoryImpl(
    private val profileDataSource: ProfileDataSource
) : ProfileRepository {
    override suspend fun getProfile(): Result<UserProfileResponse> =
        profileDataSource.getProfile()

    override suspend fun postRiotAccount(gameName: String, tagLine: String): Result<Unit> =
        profileDataSource.postRiotAccount(gameName, tagLine)
}