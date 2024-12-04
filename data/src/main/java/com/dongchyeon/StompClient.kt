package com.dongchyeon

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

object StompClient {
    private const val TAG = "StompClient"
    private const val WS_URL = "https://dev.gamelink.asia/ws-stomp/websocket"

    private val client = OkHttpClient()
    private var webSocket: WebSocket? = null

    private val _messageFlow = MutableSharedFlow<String>()
    val messageFlow = _messageFlow.asSharedFlow()

    suspend fun connect(): Boolean = withContext(Dispatchers.IO) {
        try {
            val request = Request.Builder().url(WS_URL).build()
            webSocket = client.newWebSocket(request, StompWebSocketListener())
            Log.d(TAG, "Connecting to $WS_URL")
            true
        } catch (e: Exception) {
            Log.e(TAG, "Failed to connect: ${e.message}")
            Log.e(TAG, e.printStackTrace().toString())
            false
        }
    }

    suspend fun disconnect() = withContext(Dispatchers.IO) {
        try {
            webSocket?.close(1000, "Goodbye!")
            Log.d(TAG, "Disconnected from WebSocket")
        } catch (e: Exception) {
            Log.e(TAG, "Error disconnecting: ${e.message}")
        }
    }

    fun sendMessage(destination: String, body: String) {
        try {
            val frame = """
                SEND
                destination:$destination
                content-type:application/json
                
                $body
                \u0000
            """.trimIndent()
            webSocket?.send(frame)
            Log.d(TAG, "Message sent to $destination: $body")
        } catch (e: Exception) {
            Log.e(TAG, "Error sending message: ${e.message}")
        }
    }

    private class StompWebSocketListener : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: okhttp3.Response) {
            Log.d(TAG, "WebSocket opened")
            sendConnectFrame()
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            Log.d(TAG, "Received message: $text")
            _messageFlow.tryEmit(text)
        }

        override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
            Log.d(TAG, "Received binary message")
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            Log.d(TAG, "WebSocket closing: $reason")
            webSocket.close(code, reason)
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: okhttp3.Response?) {
            Log.e(TAG, "WebSocket failure: ${t.message}")
            Log.e(TAG, t.printStackTrace().toString())
        }

        private fun sendConnectFrame() {
            val connectFrame = """
                CONNECT
                accept-version:1.1,1.2
                host:dev.gamelink.asia
                
                \u0000
            """.trimIndent()
            webSocket?.send(connectFrame)
            Log.d(TAG, "Sent CONNECT frame")
        }
    }
}