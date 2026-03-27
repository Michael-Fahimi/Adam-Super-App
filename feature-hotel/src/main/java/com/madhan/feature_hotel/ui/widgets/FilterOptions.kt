package com.madhan.feature_hotel.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.madhan.core.ui.components.PriceSelection
import com.madhan.core.ui.components.RateSelection

@Composable
fun FilterOptions(
    priceRange: MutableState<Float>,
    selectedStars: MutableState<Int>,
    selectedEquipments: MutableList<String>,
    allEquipments: List<String>
) {
    Column {
        PriceSelection(priceRange)   // price slider
        Spacer(Modifier.height(20.dp))
        RateSelection(selectedStars) // rating selector
        Spacer(Modifier.height(20.dp))
        EquipmentSelection(selectedEquipments, allEquipments) // equipment selector
    }
}
