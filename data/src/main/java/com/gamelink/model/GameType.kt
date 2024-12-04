package com.gamelink.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class GameType {
    @SerialName("SOLO_RANK")
    SOLO_RANK,
    @SerialName("FLEX_RANK")
    FLEX_RANK,
    @SerialName("NORMAL")
    NORMAL;
}