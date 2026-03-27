package com.madhan.feature_hotel.ui.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.madhan.adamsuperapp.ui.theme.descriptionColor

@Composable
fun RoomInfoRow(
    title: String,
    stayIcon: Painter,
    price: String,
    modalIcon: Painter,
    onModalClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Title
        CustomTitleText(
            modifier = Modifier.weight(1f),
            text = title,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            textAlign = TextAlign.Start
        )
        Spacer(Modifier.width(10.dp))
        // Stay Icon with Price
        IconTextRow(
            modifier = Modifier.weight(0.4f),
            icon = stayIcon,
            iconSize = 16.dp,
            iconTint = descriptionColor,
            textSize = 16.sp,
            text = price
        )
        Spacer(Modifier.width(10.dp))
        // Modal Icon Button
        CustomIconButton(
            onClick = onModalClick,
            icon = modalIcon,
            iconSize = 18.dp,
            enabled = true,
            contentDescription = null
        )
    }
}
