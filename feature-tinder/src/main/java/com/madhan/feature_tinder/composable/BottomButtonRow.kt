package com.madhan.feature_tinder.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.madhan.feature_tinder.R

@Composable
fun BottomButtonRow(
    showBack: Boolean = true,
    onDenyClicked: () -> Unit = {},
    onLikedClicked: () -> Unit = {},
    onSuperLikedClicked: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        if(showBack) {
            BottomButton(
                icon = R.drawable.ic_rewind,
                iconColor = Color(0xFF2B7FFF),
                onClick = onBack
            )
        }
        BottomButton(
            icon = R.drawable.ic_bigclose,
            iconColor = Color(0xFFEE7308),
            onClick = onDenyClicked
        )
        BottomButton(
            icon = R.drawable.ic_fire,
            iconColor = Color(0xFF5D52DC)
        )
        BottomButton(
            icon = R.drawable.ic_heart,
            iconColor = Color(0xFF4BEAC5),
            onClick = onLikedClicked
        )
        BottomButton(
            icon = R.drawable.ic_star,
            iconColor = Color(0xFFFFCB0E),
            onClick = onSuperLikedClicked
        )
    }
}


@Composable
fun BottomButton(
    @DrawableRes icon: Int,
    iconColor: Color,
    onClick: () -> Unit = {}
) {
    Surface(
        shape = CircleShape,
        shadowElevation = 6.dp,
        modifier = Modifier
            .padding(bottom = 32.dp)
            .clickable { onClick()  }
    ) {
        Box(
            modifier = Modifier
                .heightIn(min = 24.dp)
                .widthIn(min = 24.dp)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp),
                tint = iconColor
            )
        }
    }
}