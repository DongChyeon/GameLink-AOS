package com.dongchyeon.datasource

import com.dongchyeon.model.GamePosition
import com.dongchyeon.model.GameTier
import com.dongchyeon.model.GameType
import com.dongchyeon.model.response.BoolResponse
import com.dongchyeon.model.response.ChatMessageListResponse
import com.dongchyeon.model.response.ChatRoomListResponse
import com.dongchyeon.model.response.MyChatRoomListResponse
import com.dongchyeon.toSerializedName
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class ChatDataSourceImpl(
    private val client: HttpClient
) : ChatDataSource {
    override suspend fun getChatRooms(
        position: GamePosition?,
        gameType: GameType?,
        rankTiers: GameTier?,
        page: Int,
        size: Int,
        sort: List<String>?
    ): Result<ChatRoomListResponse> {
        return kotlin.runCatching {
            client.get("chatroom") {
                parameter("position", position?.toSerializedName())
                parameter("gameType", gameType?.toSerializedName())
                parameter("rankTiers", rankTiers?.toSerializedName())
                parameter("page", page)
                parameter("size", size)
                sort?.forEach { sortCriteria ->
                    parameter("sort", sortCriteria)
                }
            }.body()
        }
    }

    override suspend fun getMyChatRooms(
        page: Int,
        size: Int,
        sort: List<String>?
    ): Result<MyChatRoomListResponse> {
        return kotlin.runCatching {
            client.get("chatroom/my") {
                parameter("page", page)
                parameter("size", size)
                sort?.forEach { sortCriteria ->
                    parameter("sort", sortCriteria)
                }
            }.body()
        }
    }

    override suspend fun checkChatRoomAvailability(
        roomId: String
    ): Result<BoolResponse> {
        return kotlin.runCatching {
            client.get("chatroom/check/$roomId/enter").body()
        }
    }

    override suspend fun getChatRoomMessages(
        roomId: String,
        page: Int,
        size: Int,
        sort: List<String>?
    ): Result<ChatMessageListResponse> {
        return kotlin.runCatching {
            client.get("chatroom/message/list") {
                parameter("roomId", roomId)
                parameter("page", page)
                parameter("size", size)
                sort?.forEach { sortCriteria ->
                    parameter("sort", sortCriteria)
                }
            }.body()
        }
    }
}