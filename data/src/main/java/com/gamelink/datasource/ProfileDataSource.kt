package com.gamelink.datasource

import com.gamelink.model.response.UserProfileResponse

interface ProfileDataSource {
    suspend fun getProfile(): Result<UserProfileResponse>
    suspend fun postRiotAccount(gameName: String, tagLine: String): Result<Unit>
}