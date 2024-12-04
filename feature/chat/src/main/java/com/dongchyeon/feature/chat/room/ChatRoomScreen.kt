package com.dongchyeon.feature.chat.room

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dongchyeon.designsystem.theme.GameLinkTheme
import com.dongchyeon.feature.chat.component.ChatRoomInputBox

@Composable
internal fun ChatRoomRoute(
    showSnackBar: (String) -> Unit,
    chatRoomViewModel: ChatRoomViewModel
) {
    ChatRoomScreen(
        chatRoomViewModel = chatRoomViewModel,
        showSnackBar = showSnackBar
    )
}

@Composable
internal fun ChatRoomScreen(
    chatRoomViewModel: ChatRoomViewModel,
    showSnackBar: (String) -> Unit
) {
    val uiState by chatRoomViewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        ChatTopBar {

        }
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {

        }
        ChatRoomInputBox(
            content = uiState.content,
            onContentChange = chatRoomViewModel::updateInputContent
        ) {

        }
    }
}

@Composable
private fun ChatTopBar(
    onBack: () -> Unit
) {
    Box {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 10.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .clickable { onBack() },
                painter = painterResource(id = com.gamelink.core.designsystem.R.drawable.ic_arrow_back),
                contentDescription = "IC_ARROW_BACK",
                tint = Color.Unspecified
            )
        }

        Text(
            "채팅방",
            modifier = Modifier.align(Alignment.Center),
            style = GameLinkTheme.typography.title1,
            color = Color.White
        )
    }
}
