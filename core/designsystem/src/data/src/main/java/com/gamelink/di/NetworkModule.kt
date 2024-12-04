package com.gamelink.di

import android.util.Log
import com.gamelink.data.BuildConfig
import com.gamelink.model.response.TokenResponse
import com.gamelink.repository.UserTokenRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.encodedPath
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.koin.dsl.module

const val TAG = "KtorClient"

val networkModule = module {
    single { providesKtorClient(get()) }
}

fun providesKtorClient(
    userTokenRepository: UserTokenRepository
): HttpClient {
    val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
        isLenient = true
        encodeDefaults = true
    }

    return HttpClient(Android) {
        install(ContentNegotiation) {
            json(json)
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 15000
            connectTimeoutMillis = 15000
            socketTimeoutMillis = 15000
        }

        install(Auth) {
            bearer {
                sendWithoutRequest { request ->
                    request.url.encodedPath != "user/oauth/kakao/login"
                }

                loadTokens {
                    val accessToken = runBlocking { userTokenRepository.getAccessToken().first() }
                    val refreshToken = runBlocking { userTokenRepository.getRefreshToken().first() }

                    BearerTokens(accessToken, refreshToken)
                }

                refreshTokens {
                    val refreshToken = runBlocking { userTokenRepository.getRefreshToken().first() }

                    val token = client.post("user/oauth/token/reissue") {
                        setBody(refreshToken)
                    }.body<TokenResponse>()
                    BearerTokens(token.accessToken, token.refreshToken)
                }
            }
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d(TAG, message)
                }
            }

            level = LogLevel.ALL
        }

        defaultRequest {
            contentType(ContentType.Application.Json)
            url {
                host = BuildConfig.BASE_URL
                protocol = io.ktor.http.URLProtocol.HTTPS
            }
        }
    }
}