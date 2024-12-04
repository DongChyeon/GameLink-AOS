package com.dongchyeon.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class GamePosition {
    @SerialName("TOP")
    TOP,
    @SerialName("JUNGLE")
    JUNGLE,
    @SerialName("MID")
    MID,
    @SerialName("ADC")
    ADC,
    @SerialName("SUPPORT")
    SUPPORT,
    @SerialName("ANY")
    ANY;
}