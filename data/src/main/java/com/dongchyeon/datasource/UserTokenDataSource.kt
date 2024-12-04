package com.dongchyeon.datasource

import kotlinx.coroutines.flow.Flow

interface UserTokenDataSource {
    fun getAccessToken(): Flow<String>
    fun getRefreshToken(): Flow<String>
    suspend fun saveUserId(userId: String)
    suspend fun saveUserName(userName: String)
    suspend fun getUserId(): String
    suspend fun getUserName(): String
    suspend fun saveTokens(accessToken: String, refreshToken: String)
    suspend fun clearTokens()
}