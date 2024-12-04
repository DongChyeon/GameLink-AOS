package com.gamelink

import androidx.annotation.DrawableRes
import com.dongchyeon.feature.chat.chatRoute
import homeRoute

enum class TopLevelDestination(
    val label: String,
    @DrawableRes val icon: Int,
    val route: String
) {
    Home(
        label = "카테고리",
        icon = com.gamelink.core.designsystem.R.drawable.ic_category,
        route = homeRoute
    ),
    Chat(
        label = "채팅",
        icon = com.gamelink.core.designsystem.R.drawable.ic_chat,
        route = chatRoute
    ),
    Profile(
        label = "프로필",
        icon = com.gamelink.core.designsystem.R.drawable.ic_profile,
        route = "profile_route"
    ),
    Setting(
        label = "설정",
        icon = com.gamelink.core.designsystem.R.drawable.ic_setting,
        route = "setting_route"
    )
}