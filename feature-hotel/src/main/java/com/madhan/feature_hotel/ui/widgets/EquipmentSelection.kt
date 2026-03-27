package com.madhan.feature_hotel.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.madhan.adamsuperapp.ui.theme.PrimaryColor
import com.madhan.adamsuperapp.ui.theme.hotelTextColor

@Composable
fun EquipmentSelection(
    selectedEquipments: MutableList<String>,
    allEquipments: List<String>
) {
    Column {
        Text("Equipments", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)

        allEquipments.chunked(2).forEach { rowItems ->
            Row(modifier = Modifier.fillMaxWidth()) {
                rowItems.forEach { equipment ->
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Absolute.SpaceBetween
                    ) {
                        Text(
                            text = equipment,
                            fontWeight = if (selectedEquipments.contains(equipment)) FontWeight.Bold else FontWeight.Normal,
                            color = if (selectedEquipments.contains(equipment)) PrimaryColor else hotelTextColor
                        )
                        Checkbox(
                            checked = selectedEquipments.contains(equipment),
                            onCheckedChange = {
                                if (it) selectedEquipments.add(equipment) else selectedEquipments.remove(equipment)
                            },
                            colors = CheckboxDefaults.colors(
                                checkedColor = PrimaryColor,
                                uncheckedColor = Color.Gray
                            )
                        )
                    }
                }
            }
        }
    }
}
