package com.dongchyeon.feature.auth

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dongchyeon.common.base.BaseViewModel
import com.dongchyeon.repository.AuthRepository
import com.dongchyeon.repository.UserTokenRepository
import com.google.firebase.installations.FirebaseInstallations
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val userTokenRepository: UserTokenRepository
) : BaseViewModel<LoginContract.State, LoginContract.Event, LoginContract.Effect>(
    LoginContract.State()
) {
    override fun reduceState(event: LoginContract.Event) {
        viewModelScope.launch {
            when (event) {
                is LoginContract.Event.KakaoLoginButtonClicked -> {
                    kakaoLogin(event.context)
                }
                is LoginContract.Event.GoogleLoginButtonClicked -> { }
            }
        }
    }

    private val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            viewModelScope.launch {
                error.printStackTrace()
                postEffect(LoginContract.Effect.ShowSnackBar("카카오톡으로 로그인 실패"))
            }
        } else if (token != null) {
            processKakaoLogin(token)
        }
    }

    private fun kakaoLogin(context: Context) {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    error.printStackTrace()
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        viewModelScope.launch {
                            postEffect(LoginContract.Effect.ShowSnackBar("카카오톡으로 로그인 실패"))
                        }
                        return@loginWithKakaoTalk
                    }
                    UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                } else if (token != null) {
                    processKakaoLogin(token)
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        }
    }

    private fun processKakaoLogin(token: OAuthToken) {
        viewModelScope.launch {
            val deviceId = getDeviceId()
            authRepository.kakaoLogin(token.accessToken, deviceId)
                .onSuccess {
                    runBlocking {
                        userTokenRepository.saveTokens(it.accessToken, it.refreshToken)
                    }
                    postEffect(LoginContract.Effect.NavigateToHome)
                }
                .onFailure {
                    Log.d("LoginViewModel", it.stackTraceToString())
                    postEffect(LoginContract.Effect.ShowSnackBar("카카오톡으로 로그인 실패"))
                }
        }
    }

    private suspend fun getDeviceId(): String {
        return try {
            return FirebaseInstallations.getInstance().id.await() ?: ""
        } catch (e: Exception) {
            Log.e("LoginViewModel", "Failed to get device id", e)
            ""
        }
    }
}

