package com.dongchyeon.feature.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gamelink.designsystem.component.GButton
import com.gamelink.designsystem.component.GTextField
import com.gamelink.designsystem.theme.GameLinkTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun ProfileRoute(
    showSnackBar: (String) -> Unit,
    profileViewModel: ProfileViewModel
) {
    LaunchedEffect(Unit) {
        profileViewModel.effect.collectLatest { event ->
            when (event) {
                is ProfileContract.Effect.ShowSnackBar -> {
                    showSnackBar(event.message)
                }
            }
        }
    }

    ProfileScreen(
        profileViewModel = profileViewModel
    )
}

@Composable
internal fun ProfileScreen(
    profileViewModel: ProfileViewModel,
) {
    val uiState by profileViewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GameLinkTheme.colors.background)
    ) {
        ProfileWithNotRegistered(
            gameName = uiState.gameNameInput,
            tagLine = uiState.tagLineInput,
            updateGameName = { profileViewModel.updateGameNameInput(it) },
            updateTagLine = { profileViewModel.updateTagLineInput(it) },
            onRegisterRiotAccount = { gameName, tagLine ->
                profileViewModel.processEvent(
                    ProfileContract.Event.RegisterRiotAccount(
                        gameName, tagLine
                    )
                )
            }
        )
    }
}

@Composable
internal fun ProfileWithNotRegistered(
    gameName: String,
    tagLine: String,
    updateGameName: (String) -> Unit,
    updateTagLine: (String) -> Unit,
    onRegisterRiotAccount: (String, String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GameLinkTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "현재 등록된 계정이 없어요 :(",
                style = GameLinkTheme.typography.title1,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "계정을 등록하시면 인게임 정보를\n한 눈에 확인할 수 있어요!",
                style = GameLinkTheme.typography.body1,
                color = GameLinkTheme.colors.gray1,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(46.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                GTextField(
                    value = gameName,
                    onValueChange = updateGameName,
                    placeholderText = "닉네임을 입력해주세요"
                )

                GTextField(
                    value = tagLine,
                    onValueChange = updateTagLine,
                    placeholderText = "게임 태그를 입력해주세요"
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            GButton(
                text = "등록"
            ) {
                onRegisterRiotAccount(gameName, tagLine)
            }
        }
    }
}