package com.gamelink.datasource

import com.gamelink.model.request.DeviceInfo
import com.gamelink.model.request.KakaoInfo
import com.gamelink.model.request.KakaoLoginRequest
import com.gamelink.model.response.LoginResponse
import com.gamelink.model.response.TokenResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class AuthDataSourceImpl(
    private val client: HttpClient
) : AuthDataSource {
    override suspend fun kakaoLogin(
        deviceInfo: DeviceInfo,
        kakaoInfo: KakaoInfo
    ): Result<LoginResponse> {
        return kotlin.runCatching {
            client.post("user/oauth/kakao/login") {
                setBody(KakaoLoginRequest(deviceInfo, kakaoInfo))
            }.body<LoginResponse>()
        }
    }

    override suspend fun reissueToken(
        refreshToken: String
    ): Result<TokenResponse> {
        return kotlin.runCatching {
            client.post("user/oauth/token/reissue") {
                setBody(refreshToken)
            }.body<TokenResponse>()
        }
    }
}