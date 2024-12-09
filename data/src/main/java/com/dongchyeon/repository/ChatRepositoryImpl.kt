package com.dongchyeon.repository

import com.dongchyeon.datasource.ChatDataSource
import com.dongchyeon.model.GamePosition
import com.dongchyeon.model.GameTier
import com.dongchyeon.model.GameType
import com.dongchyeon.model.response.BoolResponse
import com.dongchyeon.model.response.ChatMessageListResponse
import com.dongchyeon.model.response.ChatRoomListResponse
import com.dongchyeon.model.response.MyChatRoomListResponse

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

    override suspend fun getMyChatRooms(
        page: Int,
        size: Int,
        sort: List<String>?
    ): Result<MyChatRoomListResponse> = chatDataSource.getMyChatRooms(page, size, sort)

    override suspend fun checkChatRoomAvailability(
        roomId: String
    ): Result<BoolResponse> = chatDataSource.checkChatRoomAvailability(roomId)

    override suspend fun getChatRoomMessages(
        roomId: String,
        page: Int,
        size: Int,
        sort: List<String>?
    ): Result<ChatMessageListResponse> = chatDataSource.getChatRoomMessages(roomId, page, size, sort)
}
