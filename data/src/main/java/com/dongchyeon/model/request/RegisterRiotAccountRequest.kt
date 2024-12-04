package com.dongchyeon.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRiotAccountRequest(
    val gameName: String,
    val tagLine: String
)
