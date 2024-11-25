package com.gamelink.repository

import com.gamelink.model.response.UserProfileResponse

interface ProfileRepository {
    suspend fun getProfile(): Result<UserProfileResponse>
    suspend fun postRiotAccount(gameName: String, tagLine: String): Result<Unit>
}