package com.dongchyeon.repository

import kotlinx.coroutines.flow.Flow

interface UserTokenRepository {
    fun getAccessToken(): Flow<String>
    fun getRefreshToken(): Flow<String>
    suspend fun saveUserId(userId: String)
    suspend fun saveUserName(userName: String)
    suspend fun getUserId(): String
    suspend fun getUserName(): String
    suspend fun saveTokens(accessToken: String, refreshToken: String)
    suspend fun clearTokens()
}