package com.dongchyeon.datasource

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.dongchyeon.datasource.UserTokenDataSourceImpl.PreferencesKeys.ACCESS_TOKEN
import com.dongchyeon.datasource.UserTokenDataSourceImpl.PreferencesKeys.REFRESH_TOKEN
import com.dongchyeon.datasource.UserTokenDataSourceImpl.PreferencesKeys.USER_ID
import com.dongchyeon.datasource.UserTokenDataSourceImpl.PreferencesKeys.USER_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserTokenDataSourceImpl(
    private val dataStore: DataStore<Preferences>
) : UserTokenDataSource {

    private object PreferencesKeys {
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        val USER_ID = stringPreferencesKey("user_id")
        val USER_NAME = stringPreferencesKey("user_name")
    }

    override fun getAccessToken(): Flow<String> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                Log.e("DataStore", "Error reading user auth token", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[ACCESS_TOKEN] ?: ""
        }
    }

    override fun getRefreshToken(): Flow<String> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                Log.e("DataStore", "Error reading user auth token", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[REFRESH_TOKEN] ?: ""
        }
    }

    override suspend fun saveUserId(userId: String) {
        dataStore.edit { preferences ->
            preferences[USER_ID] = userId
        }
    }

    override suspend fun saveUserName(userName: String) {
        dataStore.edit { preferences ->
            preferences[USER_NAME] = userName
        }
    }

    override suspend fun getUserId(): String {
        return dataStore.data.map { preferences ->
            preferences[USER_ID] ?: ""
        }.first()
    }

    override suspend fun getUserName(): String {
        return dataStore.data.map { preferences ->
            preferences[USER_NAME] ?: ""
        }.first()
    }

    override suspend fun saveTokens(accessToken: String, refreshToken: String) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = accessToken
            preferences[REFRESH_TOKEN] = refreshToken
        }
    }

    override suspend fun clearTokens() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}