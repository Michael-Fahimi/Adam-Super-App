package com.madhan.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.madhan.adamsuperapp.ui.theme.PrimaryColor

@Composable
fun PriceSelection(priceRange: MutableState<Float>) {
    Column {
        Text("Price", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        Slider(
            value = priceRange.value,
            onValueChange = { priceRange.value = it },
            valueRange = 0f..600f,
            colors = SliderDefaults.colors(
                thumbColor = PrimaryColor,
                activeTrackColor = PrimaryColor
            )
        )
        Text("$${priceRange.value.toInt()}", fontSize = 16.sp)
    }
}
