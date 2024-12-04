package com.dongchyeon.designsystem.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dongchyeon.designsystem.theme.GameLinkTheme
import com.dongchyeon.designsystem.theme.LocalColors

@Composable
fun GTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    maxLength: Int = 20,
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
            color = localColors.gray1
        ),
        cursorBrush = SolidColor(Color.White),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = localColors.gray3,
                        shape = RoundedCornerShape(8.dp)
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
fun GTextFieldPreview() {
    GameLinkTheme {
        var text by remember { mutableStateOf("") }

        GTextField(
            value = text ,
            onValueChange = { text = it },
            placeholderText = "닉네임을 입력해주세요"
        )
    }
}