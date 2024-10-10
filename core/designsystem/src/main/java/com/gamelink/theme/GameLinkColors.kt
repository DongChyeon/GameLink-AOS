package com.gamelink.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class GameLinkColors(
    primary1: Color = Color(0xFFCDFAFA),
    primary2: Color = Color(0xFF0AC8B9),
    primary3: Color = Color(0xFF0397AB),
    primary4: Color = Color(0xFF005A82),
    gray1: Color = Color(0xFFB4C2DC),
    gray2: Color = Color(0xFF56647B),
    gray3: Color = Color(0xFF454E59),
    gold1: Color = Color(0xFFF0E6D2),
    gold2: Color = Color(0xFFC8AA6E),
    gold3: Color = Color(0xFFC89B3C),
    green: Color = Color(0xFF0AC58E),
    errorRed: Color = Color(0xFFE24C4C),
    looseRed: Color = Color(0xFF4F2E34),
    winBlue: Color = Color(0xFF242E45),
    background: Color = Color(0xFF1C2023),
    background2: Color = Color(0xFF1E1E1E)
) {
    var primary1 by mutableStateOf(primary1)
        private set
    var primary2 by mutableStateOf(primary2)
        private set
    var primary3 by mutableStateOf(primary3)
        private set
    var primary4 by mutableStateOf(primary4)
        private set

    var gray1 by mutableStateOf(gray1)
        private set
    var gray2 by mutableStateOf(gray2)
        private set
    var gray3 by mutableStateOf(gray3)
        private set

    var gold1 by mutableStateOf(gold1)
        private set
    var gold2 by mutableStateOf(gold2)
        private set
    var gold3 by mutableStateOf(gold3)
        private set

    var green by mutableStateOf(green)
        private set
    var errorRed by mutableStateOf(errorRed)
        private set
    var looseRed by mutableStateOf(looseRed)
        private set
    var winBlue by mutableStateOf(winBlue)
        private set

    var background by mutableStateOf(background)
        private set
    var background2 by mutableStateOf(background2)
        private set

    fun copy(
        primary1: Color = this.primary1,
        primary2: Color = this.primary2,
        primary3: Color = this.primary3,
        primary4: Color = this.primary4,
        gray1: Color = this.gray1,
        gray2: Color = this.gray2,
        gray3: Color = this.gray3,
        gold1: Color = this.gold1,
        gold2: Color = this.gold2,
        gold3: Color = this.gold3,
        green: Color = this.green,
        errorRed: Color = this.errorRed,
        looseRed: Color = this.looseRed,
        winBlue: Color = this.winBlue,
        background: Color = this.background,
        background2: Color = this.background2
    ): GameLinkColors {
        return GameLinkColors(
            primary1 = primary1,
            primary2 = primary2,
            primary3 = primary3,
            primary4 = primary4,
            gray1 = gray1,
            gray2 = gray2,
            gray3 = gray3,
            gold1 = gold1,
            gold2 = gold2,
            gold3 = gold3,
            green = green,
            errorRed = errorRed,
            looseRed = looseRed,
            winBlue = winBlue,
            background = background,
            background2 = background2
        )
    }

    fun updateColorFrom(other: GameLinkColors) {
        primary1 = other.primary1
        primary2 = other.primary2
        primary3 = other.primary3
        primary4 = other.primary4
        gray1 = other.gray1
        gray2 = other.gray2
        gray3 = other.gray3
        gold1 = other.gold1
        gold2 = other.gold2
        gold3 = other.gold3
        green = other.green
        errorRed = other.errorRed
        looseRed = other.looseRed
        winBlue = other.winBlue
        background = other.background
        background2 = other.background2
    }
}

val LocalColors = staticCompositionLocalOf { gameLinkColors() }