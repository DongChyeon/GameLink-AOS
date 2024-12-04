package com.daon.feature.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.daon.feature.chat.component.ChatRoomItem
import com.gamelink.designsystem.theme.GameLinkTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun ChatRoute(
    showSnackBar: (String) -> Unit,
    chatViewModel: ChatViewModel
) {
    LaunchedEffect(Unit) {
        chatViewModel.effect.collectLatest { event ->
            when (event) {
                is ChatContract.Effect.ShowSnackBar -> showSnackBar(event.message)
            }
        }
    }

    ChatScreen(
        chatViewModel = chatViewModel,
        showSnackBar = showSnackBar
    )
}

@Composable
internal fun ChatScreen(
    chatViewModel: ChatViewModel,
    showSnackBar: (String) -> Unit
) {
    val uiState by chatViewModel.uiState.collectAsStateWithLifecycle()

    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collectLatest { firstVisibleItemIndex ->
                if (firstVisibleItemIndex == uiState.chatRooms.size - 1 && !uiState.isLastPage) {
                    chatViewModel.processEvent(ChatContract.Event.LoadMoreChatRooms)
                }
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GameLinkTheme.colors.background)
            .statusBarsPadding()
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            state = listState
        ) {
            items(uiState.chatRooms) { chatRoom ->
                ChatRoomItem(
                    roomId = chatRoom.roomId,
                    roomName = chatRoom.roomName,
                    leaderTier = chatRoom.leaderTier,
                    positions = chatRoom.positions,
                    onClick = { roomId ->
                    }
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = GameLinkTheme.colors.gray2)
                )
            }
        }
    }
}