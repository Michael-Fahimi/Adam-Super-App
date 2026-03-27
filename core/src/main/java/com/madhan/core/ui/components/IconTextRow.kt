package com.madhan.core.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.madhan.adamsuperapp.ui.theme.PrimaryColor
import com.madhan.adamsuperapp.ui.theme.hotelTextColor

@Composable
fun IconTextRow(
    modifier: Modifier = Modifier,
    icon: Painter,
    text: String,
    iconSize: Dp = 16.dp,
    textSize: TextUnit = 14.sp,
    textColor: Color = hotelTextColor,
    iconTint: Color = PrimaryColor
) {
    Row(
        modifier = modifier
    ) {
        Icon(
            modifier = Modifier.size(iconSize),
            painter = icon,
            contentDescription = "icon",
            tint = iconTint
        )
        Spacer(Modifier.width(10.dp))
        Text(
            text = text,
            style = TextStyle(
                fontSize = textSize,
                fontWeight = FontWeight.SemiBold,
                color = textColor,
                textAlign = TextAlign.Start
            )
        )
    }
}
