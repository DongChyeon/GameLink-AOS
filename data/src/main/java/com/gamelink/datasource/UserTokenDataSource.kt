package com.gamelink.datasource

import kotlinx.coroutines.flow.Flow

interface UserTokenDataSource {
    fun getAccessToken(): Flow<String>
    fun getRefreshToken(): Flow<String>
    suspend fun saveTokens(accessToken: String, refreshToken: String)
    suspend fun clearTokens()
}