package com.madhan.feature_delivery.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madhan.feature_delivery.R

@Composable
fun HomeIcon(onClick: () -> Unit,modifier: Modifier = Modifier,) {
    Image(
        painter = painterResource(id =R.drawable.home_2), // Replace with your actual icon resource
        contentDescription = "Home",
        modifier = Modifier
            .clickable { onClick() }
            .size(48.dp) // Adjust size as needed
            .padding(8.dp) // Add padding if needed
            .clip(CircleShape) // Clip to circle if desired
    )
}

@Preview(showBackground = true)
@Composable
fun TopLeftIconPreview() {
    HomeIcon(onClick = {}) // Corrected: Use an empty lambda
}