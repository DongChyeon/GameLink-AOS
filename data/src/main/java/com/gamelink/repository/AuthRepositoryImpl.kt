package com.gamelink.repository

import com.gamelink.datasource.AuthDataSource
import com.gamelink.model.request.DeviceInfo
import com.gamelink.model.request.KakaoInfo
import com.gamelink.model.response.LoginResponse

class AuthRepositoryImpl(
    private val authDataSource: AuthDataSource
) : AuthRepository {
    override suspend fun kakaoLogin(deviceInfo: DeviceInfo, kakaoInfo: KakaoInfo): Result<LoginResponse> =
        authDataSource.kakaoLogin(deviceInfo, kakaoInfo)
}