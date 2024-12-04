package com.gamelink.datasource

import com.gamelink.model.GamePosition
import com.gamelink.model.GameTier
import com.gamelink.model.GameType
import com.gamelink.model.response.ChatRoomListResponse

interface ChatDataSource {
    suspend fun getChatRooms(
        position: GamePosition?,
        gameType: GameType?,
        rankTiers: GameTier?,
        page: Int,
        size: Int,
        sort: List<String>?
    ): Result<ChatRoomListResponse>
}