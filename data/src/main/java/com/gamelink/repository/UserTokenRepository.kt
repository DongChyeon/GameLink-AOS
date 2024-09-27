package com.gamelink.repository

import kotlinx.coroutines.flow.Flow

interface UserTokenRepository {
    fun getAccessToken(): Flow<String>
    fun getRefreshToken(): Flow<String>
    suspend fun saveTokens(accessToken: String, refreshToken: String)
    suspend fun clearTokens()
}