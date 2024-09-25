package repository

import datasource.AuthDataSource
import model.request.DeviceInfo
import model.request.KakaoInfo
import model.response.LoginResponse

class AuthRepositoryImpl(
    private val authDataSource: AuthDataSource
) : AuthRepository {
    override suspend fun kakaoLogin(deviceInfo: DeviceInfo, kakaoInfo: KakaoInfo): Result<LoginResponse> =
        authDataSource.kakaoLogin(deviceInfo, kakaoInfo)
}