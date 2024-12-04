package com.dongchyeon.feature.chat.room

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.dongchyeon.KrossbowStompClient
import com.dongchyeon.common.base.BaseViewModel
import com.dongchyeon.repository.ChatRepository
import com.dongchyeon.repository.UserTokenRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

data class ChatMessageEntity(
    val isMine: Boolean,
    val userId: String,
    val sender: String,
    val type: String,
    val message: String,
    val fileType: String
)

class ChatRoomViewModel(
    private val chatRepository: ChatRepository,
    private val userTokenRepository: UserTokenRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<ChatRoomContract.State, ChatRoomContract.Event, ChatRoomContract.Effect>(
    ChatRoomContract.State()
) {
    private val roomId: String = savedStateHandle.get<String>("roomId") ?: ""
    private val userId: String = runBlocking { userTokenRepository.getUserId() }
    private val userName: String = runBlocking { userTokenRepository.getUserName() }

    init {
        loadMessages()
        connectToServer()
    }

    override fun reduceState(event: ChatRoomContract.Event) {
        when (event) {
            is ChatRoomContract.Event.SendMessage -> {
                sendMessage(event.message)
            }
        }
    }

    private fun loadMessages() = viewModelScope.launch {
        chatRepository.getChatRoomMessages(
            roomId = roomId,
            page = 1,
            size = 20,
            sort = null
        ).onSuccess { messages ->
            updateState(currentState.copy(messages = messages.content.map {
                ChatMessageEntity(
                    isMine = it.userId == userId,
                    userId = it.userId,
                    sender = it.nickname,
                    type = it.type.name.toUpperCase(),
                    message = it.content,
                    fileType = it.fileType?.name?.toUpperCase() ?: "NONE"
                )
            }))
            postEffect(ChatRoomContract.Effect.ScrollToBottom)
        }.onFailure {
            postEffect(ChatRoomContract.Effect.ShowSnackBar("메시지를 불러오는데 실패했습니다"))
        }
    }

    private fun connectToServer() {
        viewModelScope.launch {
            val isConnected = KrossbowStompClient.connectToServer()
            if (isConnected) {
                subscribeToRoom("/sub/chatRoom/enter$roomId")
                sendEnterMessage()
            } else {
                postEffect(ChatRoomContract.Effect.ShowSnackBar("STOMP 서버 연결 실패"))
            }
        }
    }

    private fun subscribeToRoom(topic: String) {
        viewModelScope.launch {
            KrossbowStompClient.subscribeToTopic(topic) { message ->
                Log.d("ChatRoomViewModel", "Received message: $message")
                val parsedMessage = parseMessage(message)
                Log.d("ChatRoomViewModel", "Parsed message: $parsedMessage")

                val newMessageEntity = mapJsonToChatMessageEntity(message)
                val updatedMessages = currentState.messages + newMessageEntity

                updateState(
                    currentState.copy(
                        messages = updatedMessages,
                        content = ""
                    )
                )
                postEffect(ChatRoomContract.Effect.ScrollToBottom)
            }
        }
    }

    private fun sendEnterMessage() {
        val enterMessage = JsonObject(
            mapOf(
                "roomId" to JsonPrimitive(roomId),
                "userId" to JsonPrimitive(userId),
                "sender" to JsonPrimitive(userName),
                "type" to JsonPrimitive("ENTER"),
                "message" to JsonPrimitive(""),
                "fileType" to JsonPrimitive("NONE")
            )
        )
        viewModelScope.launch {
            KrossbowStompClient.sendMessage(
                destination = "/pub/chat/enterUser",
                body = Json.encodeToString(JsonObject.serializer(), enterMessage)
            )
        }
    }

    fun sendMessage(content: String) {
        val chatMessage = JsonObject(
            mapOf(
                "roomId" to JsonPrimitive(roomId),
                "userId" to JsonPrimitive(userId),
                "sender" to JsonPrimitive(userName),
                "type" to JsonPrimitive("TALK"),
                "content" to JsonPrimitive(content),
                "fileType" to JsonPrimitive("NONE")
            )
        )
        viewModelScope.launch {
            KrossbowStompClient.sendMessage(
                destination = "/pub/chat/sendMessage",
                body = Json.encodeToString(JsonObject.serializer(), chatMessage)
            )
        }
        Log.d("ChatRoomViewModel", "Sent message: $chatMessage")
    }

    private fun parseMessage(json: String): JsonObject {
        return Json.decodeFromString(JsonObject.serializer(), json)
    }

    fun disconnect() {
        viewModelScope.launch {
            KrossbowStompClient.disconnect()
        }
    }

    fun updateInputContent(content: String) {
        updateState(currentState.copy(content = content))
    }

    private fun mapJsonToChatMessageEntity(json: String): ChatMessageEntity {
        val jsonObject = Json.parseToJsonElement(json).jsonObject

        Log.d("ChatRoomViewModel", "Mapped JSON: $jsonObject")

        val userId = jsonObject["userId"]?.jsonPrimitive?.content ?: ""
        val isMine = userId == this.userId

        return ChatMessageEntity(
            isMine = isMine,
            userId = jsonObject["userId"]?.jsonPrimitive?.content ?: "",
            sender = jsonObject["nickname"]?.jsonPrimitive?.content ?: "",
            type = jsonObject["type"]?.jsonPrimitive?.content ?: "",
            message = jsonObject["content"]?.jsonPrimitive?.content ?: "",
            fileType = jsonObject["fileType"]?.jsonPrimitive?.content ?: ""
        )
    }
}