package com.gamelink.model.request

import kotlinx.serialization.Serializable

@Serializable
data class KakaoLoginRequest(
    val deviceInfo: DeviceInfo,
    val kakaoInfo: KakaoInfo
)
