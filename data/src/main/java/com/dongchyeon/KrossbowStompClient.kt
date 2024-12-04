package com.dongchyeon

import android.util.Log
import com.gamelink.data.BuildConfig
import kotlinx.coroutines.flow.map
import org.hildan.krossbow.stomp.StompClient
import org.hildan.krossbow.stomp.StompSession
import org.hildan.krossbow.stomp.sendText
import org.hildan.krossbow.stomp.subscribeText
import org.hildan.krossbow.websocket.okhttp.OkHttpWebSocketClient

object KrossbowStompClient {

    private val stompClient = StompClient(OkHttpWebSocketClient())
    private var stompSession: StompSession? = null

    private const val TAG = "KrossbowStompClient"

    suspend fun connectToServer(): Boolean {
        return try {
            val stompUrl = BuildConfig.STOMP_URL
            stompSession = stompClient.connect(stompUrl)
            Log.d(TAG, "Connected to STOMP server at $stompUrl")
            true
        } catch (e: Exception) {
            Log.d(TAG, "Error connecting to STOMP server: ${e.message}")
            Log.d(TAG, e.printStackTrace().toString())
            false
        }
    }

    suspend fun subscribeToTopic(topic: String, onMessage: (String) -> Unit) {
        stompSession?.subscribeText(topic)?.map { it }?.collect { message ->
            onMessage(message)
        } ?: Log.d(TAG, "SubscribeToTopic : STOMP session is not connected.")
    }

    suspend fun sendMessage(destination: String, body: String) {
        stompSession?.sendText(destination, body) ?: Log.d(TAG, "SendMessage : STOMP session is not connected.")
    }

    suspend fun disconnect() {
        try {
            stompSession?.disconnect()
            println("Disconnected from STOMP server")
        } catch (e: Exception) {
            println("Error disconnecting from STOMP server: ${e.message}")
        }
    }
}