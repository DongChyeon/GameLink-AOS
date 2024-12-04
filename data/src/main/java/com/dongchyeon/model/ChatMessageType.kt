package com.dongchyeon.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ChatMessageType {
    @SerialName("ENTER")
    ENTER,
    @SerialName("TALK")
    TALK,
    @SerialName("LEAVE")
    LEAVE
}
