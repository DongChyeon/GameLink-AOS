package com.gamelink.repository

import com.gamelink.model.response.UserProfileResponse

interface ProfileRepository {
    suspend fun getProfile(): Result<UserProfileResponse>
}