package com.dongchyeon.feature.chat.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.daon.feature.chat.R
import com.dongchyeon.designsystem.theme.GameLinkTheme
import com.dongchyeon.model.GamePosition

@Composable
fun ChatRoomItem(
    roomId: String,
    roomName: String,
    lastMessageContent: String,
    profileImgUrl: String,
    onClick: (String) -> Unit
) {
    /*val tierImage = when (leaderTier) {
        GameTier.UNRANKED -> com.gamelink.core.designsystem.R.mipmap.ic_unrank
        GameTier.IRON -> com.gamelink.core.designsystem.R.mipmap.ic_iron
        GameTier.BRONZE -> com.gamelink.core.designsystem.R.mipmap.ic_bronze
        GameTier.SILVER -> com.gamelink.core.designsystem.R.mipmap.ic_silver
        GameTier.GOLD -> com.gamelink.core.designsystem.R.mipmap.ic_gold
        GameTier.PLATINUM -> com.gamelink.core.designsystem.R.mipmap.ic_platinum
        GameTier.DIAMOND -> com.gamelink.core.designsystem.R.mipmap.ic_diamond
        GameTier.MASTER -> com.gamelink.core.designsystem.R.mipmap.ic_master
        GameTier.GRANDMASTER -> com.gamelink.core.designsystem.R.mipmap.ic_grand_master
        GameTier.CHALLENGER -> com.gamelink.core.designsystem.R.mipmap.ic_challenger
        GameTier.ANY -> com.gamelink.core.designsystem.R.mipmap.ic_unrank
    }*/

    /*val positionImage = when (positions.first()) {
        GamePosition.TOP -> com.gamelink.core.designsystem.R.mipmap.ic_top
        GamePosition.JUNGLE -> com.gamelink.core.designsystem.R.mipmap.ic_jungle
        GamePosition.MID -> com.gamelink.core.designsystem.R.mipmap.ic_mid
        GamePosition.ADC -> com.gamelink.core.designsystem.R.mipmap.ic_bottom
        GamePosition.SUPPORT -> com.gamelink.core.designsystem.R.mipmap.ic_support
        GamePosition.ANY -> com.gamelink.core.designsystem.R.mipmap.ic_any
    }*/

    Box(
         modifier = Modifier.clickable {
             onClick(roomId)
         }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 16.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(profileImgUrl)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = "IMG_BACKGROUND"
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    roomName,
                    style = GameLinkTheme.typography.body1Bold,
                    color = Color.White
                )
                Text(
                    lastMessageContent,
                    style = GameLinkTheme.typography.body2,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                /*Text(
                    leaderTier,
                    style = GameLinkTheme.typography.body2,
                    color = Color.White
                )*/
            }

            Spacer(modifier = Modifier.weight(1f))

            /*AsyncImage(
                modifier = Modifier.size(40.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(positionImage)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = "IMG_TIER"
            )*/
        }
    }
}

@Preview
@Composable
fun PreviewChatRoomItem() {
    GameLinkTheme {
        ChatRoomItem(
            roomId = "1",
            roomName = "채팅방",
            lastMessageContent = "아ㅣㄴㅇㄹ멍ㄹ너ㅏㅣㅓㅣ;피;ㅓ피;ㅏㅍㅊㅌ키;ㅓㅏ",
            profileImgUrl = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
            onClick = {}
        )
    }
}