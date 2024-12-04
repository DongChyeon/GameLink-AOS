package com.dongchyeon.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BoolResponse(
    @SerialName("result")
    val result: Boolean
)
