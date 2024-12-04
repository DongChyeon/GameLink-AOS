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
    LEAVE;

    companion object {
        fun fromValue(value: String): ChatMessageType {
            return when (value) {
                "ENTER" -> ENTER
                "TALK" -> TALK
                "LEAVE" -> LEAVE
                else -> throw IllegalArgumentException("Unknown value: $value")
            }
        }
    }
}

