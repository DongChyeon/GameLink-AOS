package com.gamelink.datasource

import com.gamelink.model.request.RegisterRiotAccountRequest
import com.gamelink.model.response.UserProfileResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class ProfileDataSourceImpl(
    private val client: HttpClient
) : ProfileDataSource {
    override suspend fun getProfile(): Result<UserProfileResponse> {
        return kotlin.runCatching {
            client.get("riot/lol/account").body<UserProfileResponse>()
        }
    }

    override suspend fun postRiotAccount(gameName: String, tagLine: String): Result<Unit> {
        return kotlin.runCatching {
            client.post("riot/lol/account") {
                setBody(RegisterRiotAccountRequest(gameName, tagLine))
            }.body()
        }
    }
}