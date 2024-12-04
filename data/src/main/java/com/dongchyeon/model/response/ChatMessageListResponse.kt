package com.dongchyeon.model.response

import com.dongchyeon.model.ChatFileType
import com.dongchyeon.model.ChatMessageType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatMessageListResponse(
    @SerialName("content")
    val content: List<ChatMessageResponse>,
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
data class ChatMessageResponse(
    @SerialName("chatMessageId")
    val chatMessageId: String,
    @SerialName("userId")
    val userId: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("summonerIconUrl")
    val summonerIconUrl: String,
    @SerialName("content")
    val content: String,
    @SerialName("type")
    val type: ChatMessageType,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("fileName")
    val fileName: String?,
    @SerialName("fileUrl")
    val fileUrl: String?,
    @SerialName("fileType")
    val fileType: ChatFileType?,
    @SerialName("timeNotation")
    val timeNotation: Boolean,
    @SerialName("continuous")
    val continuous: Boolean,
    @SerialName("dateChanged")
    val dateChanged: Boolean
)
