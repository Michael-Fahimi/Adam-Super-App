package com.madhan.feature_delivery.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomOrangeButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF8000)), // Orange color
        shape = RoundedCornerShape(24.dp), // Rounded corners
        modifier = Modifier
            .fillMaxWidth(0.8f) // 80% width
            .height(50.dp) // Fixed height
            .shadow(8.dp, shape = RoundedCornerShape(24.dp)) // Adds soft shadow effect
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun PreviewCustomOrangeButton() {
    BottomOrangeButton(text = "Let'go") {
        // Handle click
    }
}
