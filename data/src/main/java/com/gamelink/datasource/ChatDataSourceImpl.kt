package com.gamelink.datasource

import com.gamelink.model.GamePosition
import com.gamelink.model.GameTier
import com.gamelink.model.GameType
import com.gamelink.model.response.ChatRoomListResponse
import com.gamelink.toSerializedName
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
}