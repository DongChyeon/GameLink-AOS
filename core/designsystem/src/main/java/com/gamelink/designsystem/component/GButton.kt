package com.gamelink.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gamelink.designsystem.theme.GameLinkTheme

@Composable
fun GButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = GameLinkTheme.colors.primary3,
            contentColor = Color.White,
            disabledContainerColor = GameLinkTheme.colors.gray2,
            disabledContentColor = Color.White
        ),
        onClick = onClick,
        enabled = enabled
    ) {
        Text(
            text = text,
            style = GameLinkTheme.typography.body2Bold
        )
    }
}

@Preview
@Composable
fun GButtonPreview() {
    GameLinkTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            GButton(
                text = "Button",
                onClick = { }
            )

            GButton(
                text = "Button",
                enabled = false,
                onClick = { }
            )
        }

    }
}