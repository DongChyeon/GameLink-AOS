package com.gamelink.feature.auth

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.gamelink.common.base.BaseViewModel
import com.gamelink.model.request.DeviceInfo
import com.gamelink.model.request.KakaoInfo
import com.gamelink.repository.AuthRepository
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
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
            viewModelScope.launch {
                authRepository.kakaoLogin(
                    DeviceInfo("", "", "", ""),
                    KakaoInfo(
                        access_token = token.accessToken,
                        expires_in = 0,
                        refresh_token = token.refreshToken,
                        refresh_token_expries = 0,
                        scope = "",
                        token_type = ""

                    )
                ).onSuccess {

                }.onFailure {
                    postEffect(LoginContract.Effect.ShowSnackBar("카카오톡으로 로그인 실패"))
                }
            }
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
                    Log.i("LoginViewModel", "accessToken: ${token.accessToken}")
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        }
    }
}

