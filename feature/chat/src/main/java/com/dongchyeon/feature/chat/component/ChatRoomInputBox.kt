package com.dongchyeon.feature.chat.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dongchyeon.designsystem.theme.GameLinkTheme
import com.dongchyeon.designsystem.theme.LocalColors

@Composable
internal fun ChatRoomInputBox(
    content: String,
    onContentChange: (String) -> Unit,
    onSend: () -> Unit
) {
    val enabled = content.isNotEmpty()

    Column(modifier = Modifier.imePadding()) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(
                    color = GameLinkTheme.colors.gray2
                )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MessageTextField(
                value = content,
                onValueChange = onContentChange,
                placeholderText = "따뜻한 댓글을 남겨주세요.",
                modifier = Modifier.weight(1f),
            )

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                painter = painterResource(id = if (enabled) {
                    com.gamelink.core.designsystem.R.drawable.ic_send_enabled
                } else {
                    com.gamelink.core.designsystem.R.drawable.ic_send_disabled
                }),
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        if (enabled) {
                            onSend()
                        }
                    },
                contentDescription = "ic_send",
                tint = Color.Unspecified
            )
        }
    }
}

@Composable
fun MessageTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    maxLength: Int = 200,
    placeholderText: String = ""
) {
    val localColors = LocalColors.current

    BasicTextField(
        value = value,
        onValueChange = {
            if (it.length <= maxLength) {
                onValueChange(it)
            }
        },
        modifier = modifier,
        textStyle = GameLinkTheme.typography.body2.copy(
            color = Color.White
        ),
        cursorBrush = SolidColor(Color.White),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = localColors.gray3,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(
                        horizontal = 16.dp,
                        vertical = 12.dp
                    )
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholderText,
                        style = GameLinkTheme.typography.body2.copy(
                            color = localColors.gray1
                        )
                    )
                }
                innerTextField()
            }
        }
    )
}

@Preview
@Composable
fun ChatRoomInputBoxPreview() {
    var content by remember { mutableStateOf("") }

    GameLinkTheme {
        ChatRoomInputBox(
            content = content,
            onContentChange = {
                content = it
            },
            onSend = {}
        )
    }
}