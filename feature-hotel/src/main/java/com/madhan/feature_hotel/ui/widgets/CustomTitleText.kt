package com.madhan.feature_hotel.ui.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.madhan.adamsuperapp.ui.theme.hotelTextColor

@Composable
fun CustomTitleText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 32.sp,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight = FontWeight.SemiBold,
    color: Color = hotelTextColor
) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = text,
        style = TextStyle(
            fontSize = fontSize,
            textAlign = textAlign,
            fontWeight = fontWeight,
            color = color
        )
    )
}
