package com.gamelink.feature.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginRoute(
    showSnackBar: (String) -> Unit,
    navigateToHome: () -> Unit,
    loginViewModel: LoginViewModel
) {
    LaunchedEffect(Unit) {
        loginViewModel.effect.collectLatest { event ->
            when (event) {
                is LoginContract.Effect.NavigateToHome -> {
                    navigateToHome()
                }
                is LoginContract.Effect.ShowSnackBar -> {
                    showSnackBar(event.message)
                }
            }
        }
    }

    LoginScreen(
        loginViewModel = loginViewModel
    )
}

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                loginViewModel.processEvent(LoginContract.Event.KakaoLoginButtonClicked(context))
            }
        ) {
            Text("Login")
        }
    }
}
