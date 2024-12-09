package com.dongchyeon.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyChatRoomListResponse(
    @SerialName("content")
    val content: List<MyChatRoomResponse>,
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
data class MyChatRoomResponse(
    @SerialName("roomId")
    val roomId: String,
    @SerialName("roomName")
    val roomName: String,
    @SerialName("lastMessageTime")
    val lastMessageTime: String?,
    @SerialName("lastMessageContent")
    val lastMessageContent: String?,
    @SerialName("users")
    val users: List<MyChatRoomUser>,
    @SerialName("newMessageCount")
    val newMessageCount: Int
)

@Serializable
data class MyChatRoomUser(
    @SerialName("userId")
    val userId: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("summonerIconUrl")
    val summonerIconUrl: String,
)