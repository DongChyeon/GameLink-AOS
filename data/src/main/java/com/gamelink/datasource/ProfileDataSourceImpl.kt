package com.gamelink.datasource

import com.gamelink.model.response.UserProfileResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ProfileDataSourceImpl(
    private val client: HttpClient
) : ProfileDataSource {
    override suspend fun getProfile(): Result<UserProfileResponse> {
        return kotlin.runCatching {
            client.get("riot/lol/account").body<UserProfileResponse>()
        }
    }
}