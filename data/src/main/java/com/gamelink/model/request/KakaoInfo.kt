package com.gamelink.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KakaoInfo(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("expires_in")
    val expiresIn: Int,
    @SerialName("refresh_token")
    val refreshToken: String,
    @SerialName("refresh_token_expires")
    val refreshTokenExpires: Int,
    @SerialName("scope")
    val scope: String,
    @SerialName("token_type")
    val tokenType: String
)
