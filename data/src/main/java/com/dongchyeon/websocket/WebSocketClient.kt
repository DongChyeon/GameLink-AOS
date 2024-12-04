package com.dongchyeon.websocket

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
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

object WebSocketClient {

    private const val TAG = "WebSocket"

    private val client = HttpClient(Android) {
        install(WebSockets)
        install(Logging) {
            level = LogLevel.ALL
        }
    }

    private var session: WebSocketSession? = null

    suspend fun connectToWebSocket() {
        try {
            client.webSocket(host = "server.address", port = 8080, path = "/ws") {
                session = this
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error connecting to WebSocket", e)
        }
    }

    suspend fun sendMessage(message: String) {
        try {
            session?.send(Frame.Text(message)) ?: Log.d("WebSocket", "Session is null")
        } catch (e: Exception) {
            Log.e(TAG, "Error sending message", e)
        }
    }

    fun observeMessages(): Flow<String> {
        return try {
            session?.incoming
                ?.receiveAsFlow()
                ?.map { (it as Frame.Text).readText() }
                ?: flow { }
        } catch (e: Exception) {
            Log.e(TAG, "Error observing messages", e)
            flow { }
        }
    }

    suspend fun closeWebSocket() {
        try {
            session?.close()
            client.close()
            Log.d(TAG, "WebSocket closed")
        } catch (e: Exception) {
            Log.e(TAG, "Error closing WebSocket", e)
        }
    }
}