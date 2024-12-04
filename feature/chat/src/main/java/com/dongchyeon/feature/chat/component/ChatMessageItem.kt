package com.dongchyeon.feature.chat.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.G
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dongchyeon.designsystem.theme.GameLinkTheme

@Composable
fun CenterChatMessageItem(
    modifier: Modifier = Modifier,
    message: String
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            modifier = Modifier
                .background(
                    color = Color.Black.copy(alpha = 0.3f),
                    shape = CircleShape
                )
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
            style = GameLinkTheme.typography.body1,
            color = Color.White
        )
    }
}

@Composable
fun LeftChatMessageItem(
    modifier: Modifier = Modifier,
    from: String,
    message: String
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = from,
            style = GameLinkTheme.typography.body2,
            color = GameLinkTheme.colors.gray2
        )

        Text(
            modifier = Modifier
                .background(
                    color = GameLinkTheme.colors.gray3,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
            text = message,
            style = GameLinkTheme.typography.body1,
            color = Color.White
        )
    }
}

@Composable
fun RightChatMessageItem(
    modifier: Modifier = Modifier,
    message: String
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            modifier = Modifier
                .background(
                    color = GameLinkTheme.colors.primary1,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
            text = message,
            style = GameLinkTheme.typography.body1,
            color = Color.Black
        )
    }
}

@Preview
@Composable
fun PreviewCenterChatMessageItem() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        CenterChatMessageItem(
            message = "동현님이 들어오셨습니다"
        )

        LeftChatMessageItem(
            from = "동현",
            message = "으ㅜ아아아아아악 살려줘"
        )

        RightChatMessageItem(
            message = "안녕하세요!"
        )
    }
}