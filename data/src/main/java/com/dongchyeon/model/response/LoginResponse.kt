package com.dongchyeon.model.response

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val userId: String,
    val accessToken: String,
    val refreshToken: String,
    val uniqueId: String
)
