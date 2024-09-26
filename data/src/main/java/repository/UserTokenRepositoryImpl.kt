package repository

import datasource.UserTokenDataSource
import kotlinx.coroutines.flow.Flow

class UserTokenRepositoryImpl(
    private val userTokenDataSource: UserTokenDataSource
) : UserTokenRepository {
    override fun getAccessToken(): Flow<String> {
        return userTokenDataSource.getAccessToken()
    }

    override fun getRefreshToken(): Flow<String> {
        return userTokenDataSource.getRefreshToken()
    }

    override suspend fun saveTokens(accessToken: String, refreshToken: String) {
        userTokenDataSource.saveTokens(accessToken, refreshToken)
    }

    override suspend fun clearTokens() {
        userTokenDataSource.clearTokens()
    }
}