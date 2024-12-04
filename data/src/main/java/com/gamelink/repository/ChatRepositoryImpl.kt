package com.gamelink.repository

import com.gamelink.datasource.ChatDataSource
import com.gamelink.model.GamePosition
import com.gamelink.model.GameTier
import com.gamelink.model.GameType
import com.gamelink.model.response.ChatRoomListResponse

class ChatRepositoryImpl(
    private val chatDataSource: ChatDataSource
) : ChatRepository {
    override suspend fun getChatRooms(
        position: GamePosition?,
        gameType: GameType?,
        rankTiers: GameTier?,
        page: Int,
        size: Int,
        sort: List<String>?
    ): Result<ChatRoomListResponse> = chatDataSource.getChatRooms(position, gameType, rankTiers, page, size, sort)

}
