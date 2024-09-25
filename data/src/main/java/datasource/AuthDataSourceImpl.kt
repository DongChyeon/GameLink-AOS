package datasource

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.util.InternalAPI
import model.request.DeviceInfo
import model.request.KakaoInfo
import model.response.LoginResponse

class AuthDataSourceImpl(
    private val client: HttpClient
) : AuthDataSource {
    @OptIn(InternalAPI::class)
    override suspend fun kakaoLogin(
        deviceInfo: DeviceInfo,
        kakaoInfo: KakaoInfo
    ): Result<LoginResponse> {
        return kotlin.runCatching {
            client.post("user/oauth/kakao/login") {
                contentType(ContentType.Application.Json)
                body = mapOf(
                    "deviceInfo" to deviceInfo,
                    "kakaoInfo" to kakaoInfo
                )
            }.body<LoginResponse>()
        }
    }
}