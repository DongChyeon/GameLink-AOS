package com.gamelink.repository

import com.gamelink.model.request.DeviceInfo
import com.gamelink.model.request.KakaoInfo
import com.gamelink.model.response.LoginResponse
import com.gamelink.model.response.TokenResponse

interface AuthRepository {
    suspend fun kakaoLogin(
        deviceInfo: DeviceInfo,
        kakaoInfo: KakaoInfo
    ) : Result<LoginResponse>

    suspend fun reissueToken(
        refreshToken: String
    ) : Result<TokenResponse>
}