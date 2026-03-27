package com.madhan.feature_pet.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    minValue: Float = 0f,
    maxValue: Float = 100f,
    startLabel: String = "Min",
    middleLabel: String = "Medium",
    endLabel: String = "Max"
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = startLabel, fontSize = 14.sp, color = Color.Gray)
            Text(text = middleLabel, fontSize = 16.sp, color = Color.Black)
            Text(text = endLabel, fontSize = 14.sp, color = Color.Gray)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = minValue..maxValue,
            modifier = Modifier.fillMaxWidth(),
            colors = SliderDefaults.colors(
                thumbColor = Color(0xFFFF8C00),
                activeTrackColor = Color.LightGray,
                inactiveTrackColor = Color.Gray
            )
        )
    }
}

@Preview
@Composable
fun FilterSliderPreview() {
    var sliderPosition by remember { mutableStateOf(50f) }
    FilterSlider(
        value = sliderPosition,
        onValueChange = { sliderPosition = it },
        minValue = 0f,
        maxValue = 100f,
        startLabel = "Low",
        middleLabel = "Medium",
        endLabel = "High"
    )
}
