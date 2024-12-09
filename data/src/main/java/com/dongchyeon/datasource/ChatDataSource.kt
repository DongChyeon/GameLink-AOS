package com.dongchyeon.datasource

import com.dongchyeon.model.GamePosition
import com.dongchyeon.model.GameTier
import com.dongchyeon.model.GameType
import com.dongchyeon.model.request.SelectPositionRequest
import com.dongchyeon.model.response.BoolResponse
import com.dongchyeon.model.response.ChatMessageListResponse
import com.dongchyeon.model.response.ChatRoomListResponse
import com.dongchyeon.model.response.MyChatRoomListResponse

interface ChatDataSource {
    suspend fun getChatRooms(
        position: GamePosition?,
        gameType: GameType?,
        rankTiers: GameTier?,
        page: Int,
        size: Int,
        sort: List<String>?
    ): Result<ChatRoomListResponse>

    suspend fun getMyChatRooms(
        page: Int,
        size: Int,
        sort: List<String>?
    ): Result<MyChatRoomListResponse>

    suspend fun checkChatRoomAvailability(
        roomId: String
    ): Result<BoolResponse>

    suspend fun getChatRoomMessages(
        roomId: String,
        page: Int,
        size: Int,
        sort: List<String>?
    ): Result<ChatMessageListResponse>
}