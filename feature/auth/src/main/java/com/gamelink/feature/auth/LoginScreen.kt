package com.gamelink.feature.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.LinkInteractionListener
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gamelink.theme.GameLinkTheme
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .background(GameLinkTheme.colors.background)
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "GameLink",
            modifier = Modifier.padding(top = 150.dp),
            style = GameLinkTheme.typography.head1.copy(
                color = GameLinkTheme.colors.primary2
            )
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            KakaoLoginButton {
                loginViewModel.processEvent(LoginContract.Event.KakaoLoginButtonClicked(context))
            }

            val annotatedString = buildAnnotatedString {
                append("첫 로그인 시, ")
                withLink(LinkAnnotation.Url(
                    url = "",
                    styles = TextLinkStyles(
                        style = SpanStyle(
                            textDecoration = TextDecoration.Underline
                        )
                    ),
                    linkInteractionListener = LinkInteractionListener {

                    }
                )) {
                    append("서비스 이용약관")
                }
                append("에 동의한 것으로 간주합니다.")
            }

            Text(
                text = annotatedString,
                style = GameLinkTheme.typography.navi.copy(
                    color = GameLinkTheme.colors.gray2
                )
            )

            Spacer(modifier = Modifier.height(14.dp))
        }
    }


}

@Preview
@Composable
private fun KakaoLoginButton(
    onClick: () -> Unit = {}
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(
            horizontal = 24.dp,
            vertical = 18.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFBE400)
        ),
        shape = RoundedCornerShape(8.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(com.gamelink.core.designsystem.R.drawable.ic_kakao),
                contentDescription = null,
                tint = Color.Unspecified
            )

            Text(
                "카카오로 3초만에 시작하기",
                modifier = Modifier.fillMaxWidth(),
                style = GameLinkTheme.typography.body1.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF3E1A1D)
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}