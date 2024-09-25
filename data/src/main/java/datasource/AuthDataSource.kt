package datasource

import model.request.DeviceInfo
import model.request.KakaoInfo
import model.response.LoginResponse

interface AuthDataSource {
    suspend fun kakaoLogin(
        deviceInfo: DeviceInfo,
        kakaoInfo: KakaoInfo
    ) : Result<LoginResponse>
}