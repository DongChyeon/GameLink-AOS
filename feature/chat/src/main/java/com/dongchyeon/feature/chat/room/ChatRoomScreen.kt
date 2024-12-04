package com.dongchyeon.feature.chat.room

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dongchyeon.designsystem.theme.GameLinkTheme
import com.dongchyeon.feature.chat.component.CenterChatMessageItem
import com.dongchyeon.feature.chat.component.ChatRoomInputBox
import com.dongchyeon.feature.chat.component.LeftChatMessageItem
import com.dongchyeon.feature.chat.component.RightChatMessageItem
import com.dongchyeon.model.ChatMessageType
import kotlinx.coroutines.flow.collectLatest
import kotlin.math.max

@Composable
internal fun ChatRoomRoute(
    showSnackBar: (String) -> Unit,
    chatRoomViewModel: ChatRoomViewModel
) {
    DisposableEffect(Unit) {
        onDispose {
            chatRoomViewModel.disconnect()
        }
    }

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

    val effectFlow = chatRoomViewModel.effect

    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        effectFlow.collectLatest { effect ->
            when (effect) {
                is ChatRoomContract.Effect.ScrollToBottom -> {
                    val totalItemCount = listState.layoutInfo.totalItemsCount
                    listState.animateScrollToItem(max(totalItemCount - 1, 0))
                }

                is ChatRoomContract.Effect.ShowSnackBar -> {
                    showSnackBar(effect.message)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        ChatTopBar {

        }
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(
                    horizontal = 20.dp,
                    vertical = 16.dp
                ),
            state = listState,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.messages) { message ->
                when (ChatMessageType.fromValue(message.type)) {
                    ChatMessageType.ENTER -> {
                        CenterChatMessageItem(message = "${message.sender}님이 들어오셨습니다")
                    }
                    ChatMessageType.LEAVE -> {
                        CenterChatMessageItem(message = "${message.sender}님이 나가셨습니다")
                    }
                    ChatMessageType.TALK -> {
                        if (message.isMine) {
                            RightChatMessageItem(message = message.message)
                        } else {
                            LeftChatMessageItem(from = message.sender, message = message.message)
                        }
                    }
                }
            }
        }
        ChatRoomInputBox(
            content = uiState.content,
            onContentChange = chatRoomViewModel::updateInputContent
        ) {
            chatRoomViewModel.sendMessage(
                uiState.content
            )
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
