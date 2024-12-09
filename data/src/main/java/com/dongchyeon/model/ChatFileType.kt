package com.dongchyeon.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ChatFileType {
    @SerialName("FILE")
    FILE,
    @SerialName("IMAGE")
    IMAGE,
    @SerialName("NONE")
    NONE
}