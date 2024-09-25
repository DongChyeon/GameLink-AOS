package repository

import model.request.DeviceInfo
import model.request.KakaoInfo
import model.response.LoginResponse

interface AuthRepository {
    suspend fun kakaoLogin(
        deviceInfo: DeviceInfo,
        kakaoInfo: KakaoInfo
    ) : Result<LoginResponse>
}