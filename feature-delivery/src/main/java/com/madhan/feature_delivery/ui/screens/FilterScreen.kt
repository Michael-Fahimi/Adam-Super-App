package com.madhan.feature_delivery.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.madhan.feature_delivery.ui.components.BottomOrangeButton
import com.madhan.feature_delivery.ui.components.CustomBackButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomBackButton { /* Handle click*/
                navController.popBackStack()}
            Text(text = "Filters", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            TextButton(onClick = { /* Handle clear button click */ }) {
                Text(text = "Clear", fontSize = 15.sp, color = Color(0xFFFF8000))
            }
        }

        Spacer(modifier = Modifier.height(36.dp))

        // Sort By Dropdown
        var expanded by remember { mutableStateOf(false) }
        var selectedOptionText by remember { mutableStateOf("Recommend") }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                readOnly = true,
                value = selectedOptionText,
                onValueChange = { },
                label = { Text("Sort by") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.fillMaxWidth().menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                listOf("Recommend", "Price", "Rating").forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            selectedOptionText = selectionOption
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(26.dp))

        // Price/kg Slider
        Text(text = "Price/kg")
        var sliderPosition by remember { mutableFloatStateOf(50f) }
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = 0f..100f,
            colors = SliderDefaults.colors(
                thumbColor = Color(0xFFFF8000),
                activeTrackColor = Color(0xFFFF8000)
            )

        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("0")
            Text("50")
            Text("100")
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Rate Stars
        Text(text = "Rate")
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            repeat(5) { index ->
                Icon(
                    imageVector = if (index < 4) Icons.Filled.Star else Icons.Outlined.Star,
                    contentDescription = null,
                    tint = if (index < 4) Color(0xFFFF8000) else Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        Row(horizontalArrangement = Arrangement.Absolute.Center,
            modifier = Modifier.fillMaxWidth() ) {

            // Apply Button
            BottomOrangeButton("Apply") {
                navController.navigate("delivery_men")

            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun FilterScreenPreview() {
    FilterScreen(rememberNavController())
}