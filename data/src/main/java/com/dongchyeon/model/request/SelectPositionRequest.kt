package com.dongchyeon.model.request

import com.dongchyeon.model.GamePosition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SelectPositionRequest(
    @SerialName("myPosition")
    val myPosition: GamePosition,
)
