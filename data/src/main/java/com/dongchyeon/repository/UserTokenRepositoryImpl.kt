package com.dongchyeon.repository

import com.dongchyeon.datasource.UserTokenDataSource
import kotlinx.coroutines.flow.Flow

class UserTokenRepositoryImpl(
    private val userTokenDataSource: UserTokenDataSource
) : UserTokenRepository {
    override fun getAccessToken(): Flow<String> {
        return userTokenDataSource.getAccessToken()
    }

    override fun getRefreshToken(): Flow<String> {
        return userTokenDataSource.getRefreshToken()
    }

    override suspend fun saveUserId(userId: String) {
        userTokenDataSource.saveUserId(userId)
    }

    override suspend fun saveUserName(userName: String) {
        userTokenDataSource.saveUserName(userName)
    }

    override suspend fun getUserId(): String {
        return userTokenDataSource.getUserId()
    }

    override suspend fun getUserName(): String {
        return userTokenDataSource.getUserName()
    }

    override suspend fun saveTokens(accessToken: String, refreshToken: String) {
        userTokenDataSource.saveTokens(accessToken, refreshToken)
    }

    override suspend fun clearTokens() {
        userTokenDataSource.clearTokens()
    }
}