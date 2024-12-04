package com.dongchyeon.datasource

import com.dongchyeon.model.response.LoginResponse
import com.dongchyeon.model.response.TokenResponse

interface AuthDataSource {
    suspend fun kakaoLogin(
        accessToken: String,
        deviceId: String
    ) : Result<LoginResponse>

    suspend fun reissueToken(
        refreshToken: String
    ) : Result<TokenResponse>
}