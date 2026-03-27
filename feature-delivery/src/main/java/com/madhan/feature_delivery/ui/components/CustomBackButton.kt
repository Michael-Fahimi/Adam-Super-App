package com.madhan.feature_delivery.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madhan.feature_delivery.R

@Composable
fun CustomBackButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(48.dp) // Match the image size
            .clip(CircleShape)
            .clickable { onClick() }
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.back), //  drawable
            contentDescription = "Back Button",
            modifier = Modifier.size(48.dp) // Match image dimensions
        )
    }
}

@Preview
@Composable
fun PreviewCustomBackButton() {
    CustomBackButton { /* Handle Click */ }
}
