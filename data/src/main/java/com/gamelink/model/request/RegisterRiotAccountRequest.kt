package com.gamelink.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRiotAccountRequest(
    val gameName: String,
    val tagLine: String
)
