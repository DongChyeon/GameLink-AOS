package com.dongchyeon.datasource

import com.dongchyeon.model.request.RegisterRiotAccountRequest
import com.dongchyeon.model.response.UserProfileResponse
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
            client.post("riot/lol/account/register") {
                setBody(RegisterRiotAccountRequest(gameName, tagLine))
            }.body()
        }
    }
}