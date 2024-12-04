package com.dongchyeon.repository

import com.dongchyeon.datasource.AuthDataSource
import com.dongchyeon.model.response.LoginResponse
import com.dongchyeon.model.response.TokenResponse

class AuthRepositoryImpl(
    private val authDataSource: AuthDataSource
) : AuthRepository {
    override suspend fun kakaoLogin(accessToken: String, deviceId: String): Result<LoginResponse> =
        authDataSource.kakaoLogin(accessToken, deviceId)

    override suspend fun reissueToken(refreshToken: String): Result<TokenResponse> =
        authDataSource.reissueToken(refreshToken)
}