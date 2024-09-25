package model.request

data class KakaoInfo(
    val access_token: String,
    val expires_in: Int,
    val refresh_token: String,
    val refresh_token_expries: Int,
    val scope: String,
    val token_type: String
)
