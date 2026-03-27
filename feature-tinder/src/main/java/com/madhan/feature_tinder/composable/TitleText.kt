package com.madhan.feature_tinder.composable

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
fun TitleText(
    text: String,
    style: TextStyle? = null
) {
    Text(
        text = text,
        style = style ?: MaterialTheme.typography.displaySmall,
        fontWeight = FontWeight.Bold
    )
}