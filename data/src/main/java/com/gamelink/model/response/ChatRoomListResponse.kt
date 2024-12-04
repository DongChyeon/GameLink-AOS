package com.gamelink.model.response

import com.gamelink.model.GamePosition
import com.gamelink.model.GameTier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatRoomListResponse(
    @SerialName("content")
    val content: List<ChatRoomResponse>,
    @SerialName("hasNext")
    val hasNext: Boolean,
    @SerialName("totalPages")
    val totalPages: Int,
    @SerialName("totalElements")
    val totalElements: Int,
    @SerialName("page")
    val page: Int,
    @SerialName("size")
    val size: Int,
    @SerialName("first")
    val first: Boolean,
    @SerialName("last")
    val last: Boolean
)

@Serializable
data class ChatRoomResponse(
    @SerialName("roomId")
    val roomId: String,
    @SerialName("roomName")
    val roomName: String,
    @SerialName("userCount")
    val userCount: Int,
    @SerialName("maxUserCount")
    val maxUserCount: Int,
    @SerialName("leaderTier")
    val leaderTier: String,
    @SerialName("positions")
    val positions: List<GamePosition>
)