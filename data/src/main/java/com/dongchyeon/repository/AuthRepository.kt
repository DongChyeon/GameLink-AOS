package com.dongchyeon.repository

import com.dongchyeon.model.response.LoginResponse
import com.dongchyeon.model.response.TokenResponse

interface AuthRepository {
    suspend fun kakaoLogin(
        accessToken: String,
        deviceId: String
    ) : Result<LoginResponse>

    suspend fun reissueToken(
        refreshToken: String
    ) : Result<TokenResponse>
}