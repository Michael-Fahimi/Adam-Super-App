package com.madhan.feature_tinder.composable

import androidx.compose.ui.graphics.Color

enum class CardBgColor(val color: Color) {
    NONE(Color.Transparent),
    REJECTED(Color(0xFFEE7308)),
    LIKED(Color(0xFF4BEAC5)),
    SUPERLIKED(Color(0xFFFFCB0E))
}
