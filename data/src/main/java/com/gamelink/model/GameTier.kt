package com.gamelink.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

enum class GameTier {
    @SerialName("IRON")
    IRON,
    @SerialName("BRONZE")
    BRONZE,
    @SerialName("SILVER")
    SILVER,
    @SerialName("GOLD")
    GOLD,
    @SerialName("PLATINUM")
    PLATINUM,
    @SerialName("DIAMOND")
    DIAMOND,
    @SerialName("MASTER")
    MASTER,
    @SerialName("GRANDMASTER")
    GRANDMASTER,
    @SerialName("CHALLENGER")
    CHALLENGER,
    @SerialName("UNRANKED")
    UNRANKED,
    @SerialName("ANY")
    ANY;
}