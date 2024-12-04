package com.dongchyeon.repository

import com.dongchyeon.model.response.UserProfileResponse

interface ProfileRepository {
    suspend fun getProfile(): Result<UserProfileResponse>
    suspend fun postRiotAccount(gameName: String, tagLine: String): Result<Unit>
}