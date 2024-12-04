package com.dongchyeon.model.request

import kotlinx.serialization.Serializable

@Serializable
data class KakaoLoginRequest(
    val accessToken: String,
    val deviceId: String
)
