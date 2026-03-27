package com.madhan.core.ui.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PrimaryButton(
    text: String,
    textColor: Color = Color.White,
    buttonBorder: BorderStroke = BorderStroke(
        1.dp,
       Color.LightGray
    ),
    onClick: () -> Unit,
    colors: ButtonColors = ButtonDefaults.buttonColors(Color(0xFFFF7A1A)), // Default to orange
    width: Dp = 257.dp,
    height: Dp = 56.dp,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Button(
            onClick = onClick,
            enabled = true,
            colors = colors,
            shape = RoundedCornerShape(10.dp),
            border = buttonBorder,
            modifier = Modifier
                .width(width)
                .height(height)
                .align(Alignment.Center)
        ) {
            Text(
                text,
                style = TextStyle(
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    color = textColor
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrimaryButtonPreview() {
    PrimaryButton(
        text = "Click Me",
        onClick = {}
    )
}