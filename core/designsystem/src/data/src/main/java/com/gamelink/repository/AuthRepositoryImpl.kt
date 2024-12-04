package com.gamelink.repository

import com.gamelink.datasource.AuthDataSource
import com.gamelink.model.response.LoginResponse
import com.gamelink.model.response.TokenResponse

class AuthRepositoryImpl(
    private val authDataSource: AuthDataSource
) : AuthRepository {
    override suspend fun kakaoLogin(accessToken: String, deviceId: String): Result<LoginResponse> =
        authDataSource.kakaoLogin(accessToken, deviceId)

    override suspend fun reissueToken(refreshToken: String): Result<TokenResponse> =
        authDataSource.reissueToken(refreshToken)
}