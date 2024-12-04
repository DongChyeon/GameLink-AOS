package com.dongchyeon

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow

object KtorStompClient {

    private const val TAG = "StompClient"
    private const val WS_URL = "https://dev.gamelink.asia/ws-stomp/websocket" // Ensure it's the correct WebSocket endpoint

    private val client = HttpClient(CIO) {
        install(WebSockets)
        install(Logging) {
            level = LogLevel.ALL
        }
    }

    private var session: WebSocketSession? = null

    suspend fun connect(roomId: String, userId: String, username: String) {
        try {
            client.webSocket(WS_URL) {
                session = this
                sendConnectFrame()
                subscribeToRoom(roomId)
                sendEnterMessage(roomId, userId, username)
                Log.d(TAG, "STOMP connection established")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error connecting to STOMP WebSocket", e)
        }
    }

    private suspend fun sendConnectFrame() {
        session?.send(
            Frame.Text(
                "CONNECT\naccept-version:1.1,1.2\nhost:dev.gamelink.asia\n\n\u0000"
            )
        )
    }

    private suspend fun subscribeToRoom(roomId: String) {
        session?.send(
            Frame.Text(
                "SUBSCRIBE\nid:sub-$roomId\ndestination:/sub/chatRoom/enter$roomId\n\n\u0000"
            )
        )
        Log.d(TAG, "Subscribed to room: $roomId")
    }

    private suspend fun sendEnterMessage(roomId: String, userId: String, username: String) {
        sendMessage(
            destination = "/pub/chat/enterUser",
            body = """{
                "roomId": "$roomId",
                "userId": "$userId",
                "sender": "$username",
                "type": "ENTER",
                "fileType": "NONE"
            }"""
        )
        Log.d(TAG, "Enter message sent for user: $username in room: $roomId")
    }

    suspend fun sendMessage(destination: String, body: String) {
        try {
            val frame = "SEND\ndestination:$destination\ncontent-type:application/json\n\n$body\u0000"
            session?.send(Frame.Text(frame))
            Log.d(TAG, "Message sent to $destination: $body")
        } catch (e: Exception) {
            Log.e(TAG, "Error sending message", e)
        }
    }

    fun observeMessages(): Flow<String> {
        return session?.incoming?.receiveAsFlow()?.map {
            (it as Frame.Text).readText()
        } ?: flow { }
    }

    suspend fun disconnect() {
        try {
            session?.send(Frame.Text("DISCONNECT\n\n\u0000"))
            session?.close()
            client.close()
            Log.d(TAG, "STOMP connection closed")
        } catch (e: Exception) {
            Log.e(TAG, "Error closing STOMP WebSocket", e)
        }
    }
}