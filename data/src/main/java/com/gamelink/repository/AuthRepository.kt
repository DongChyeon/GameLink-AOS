package com.gamelink.repository

import com.gamelink.model.response.LoginResponse
import com.gamelink.model.response.TokenResponse

interface AuthRepository {
    suspend fun kakaoLogin(
        accessToken: String,
        deviceId: String
    ) : Result<LoginResponse>

    suspend fun reissueToken(
        refreshToken: String
    ) : Result<TokenResponse>
}