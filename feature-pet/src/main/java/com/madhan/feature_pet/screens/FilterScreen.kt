package com.madhan.feature_pet.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.madhan.core.ui.components.RateSelection
import com.madhan.feature_pet.R
import com.madhan.feature_pet.composable.FilterSlider
import com.madhan.feature_pet.composable.PetButton
import com.madhan.feature_pet.composable.PetTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterScreen(navController: NavHostController) {
    var expandedSort by remember { mutableStateOf(false) }
    var selectedSort by remember { mutableStateOf("Recommend") }
    var pricePerHour by remember { mutableFloatStateOf(30f) }
    var pricePerDay by remember { mutableFloatStateOf(300f) }
    var rating by remember { mutableFloatStateOf(4.5f) }
    val sortOptions = listOf("Recommend", "Newest", "Oldest", "Popular")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.navigate("dog_list") }) { // Navigate to DogListScreen
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color(0xFFFF8C00))
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "Filters", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier = Modifier.weight(1f))
            TextButton(onClick = { /* Clear Filters */ }) {
                Text(text = "Clear", fontSize = 16.sp, color = Color(0xFFFF8C00))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sort Dropdown
        Spacer(modifier = Modifier.height(8.dp))

        ExposedDropdownMenuBox(
            expanded = expandedSort,
            onExpandedChange = { expandedSort = !expandedSort }
        ) {
            PetTextField(
                label = "Sort by",
                value = selectedSort,
                onValueChange = {}
            )

            ExposedDropdownMenu(expanded = expandedSort, onDismissRequest = { expandedSort = false }) {
                sortOptions.forEach { sort ->
                    DropdownMenuItem(
                        text = { Text(sort) },
                        onClick = {
                            selectedSort = sort
                            expandedSort = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Price/hour Slider
        FilterSectionTitle(title = "Price/hour")
        FilterSlider(
            value = pricePerHour,
            onValueChange = { pricePerHour = it },
            minValue = 0f,
            maxValue = 60f,
            startLabel = "0",
            endLabel = "60"
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Price/Day Slider
        FilterSectionTitle(title = "Price/Day")
        FilterSlider(
            value = pricePerDay,
            onValueChange = { pricePerDay = it },
            minValue = 0f,
            maxValue = 600f,
            startLabel = "0",
            endLabel = "600"
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Rating Section
        FilterSectionTitle(title = "Rate")

// Define a state for the selected stars
        val selectedStars = remember { mutableStateOf(0) }

        RateSelection(selectedStars = selectedStars)

        Spacer(modifier = Modifier.height(180.dp))

        // Apply Button
        PetButton(text = "Apply") {
            navController.navigate("pet_care_list") // Navigate to PetCarePersonListScreen
        }
    }
}

@Composable
fun FilterSectionTitle(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.padding(end = 8.dp)
        )
        Divider(
            color = Color.Gray,
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
        )
    }
}

@Preview
@Composable
fun FilterScreenPreview() {
    val navController = rememberNavController()
    FilterScreen(navController)
}

