package com.madhan.feature_pet.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.madhan.feature_pet.composable.PetButton
import com.madhan.feature_pet.composable.PetTextField
import com.madhan.feature_pet.composable.FilterSlider
import com.madhan.feature_pet.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YourDogDetails(navController: NavHostController) {
    var name by remember { mutableStateOf("Kobe") }
    var selectedAge by remember { mutableStateOf("10") }
    var selectedRace by remember { mutableStateOf("Pug") }
    var isMale by remember { mutableStateOf(true) }
    val raceOptions = listOf("Pug", "Mix", "Shepperd", "Golden Retriever", "Husky")
    val ageOptions = (1..20).map { it.toString() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.navigate("start") }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_home),
                    contentDescription = "Home Icon",
                    tint = Color(0xFFFF8C00),
                    modifier = Modifier.size(28.dp)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Your dog",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        PetTextField(label = "Name", value = name, onValueChange = { name = it })
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Race",
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

        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            raceOptions.forEach { race ->
                Button(
                    onClick = { selectedRace = race },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.shadow(4.dp, RoundedCornerShape(20.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedRace == race) Color(0xFFFF8C00) else Color.LightGray
                    )
                ) {
                    Text(text = race, color = if (selectedRace == race) Color.White else Color.Black)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Sex",
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

        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = isMale,
                    onCheckedChange = { isMale = true },
                    colors = CheckboxDefaults.colors(checkmarkColor = Color.White, checkedColor = Color(0xFFFF8C00))
                )
                Text(text = "Male", fontSize = 16.sp, color = Color.Black)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = !isMale,
                    onCheckedChange = { isMale = false },
                    colors = CheckboxDefaults.colors(checkmarkColor = Color.White, checkedColor = Color(0xFFFF8C00))
                )
                Text(text = "Female", fontSize = 16.sp, color = Color.Black)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Spacer(modifier = Modifier.height(8.dp))
        var expanded by remember { mutableStateOf(false) }
        val focusRequester = remember { FocusRequester() }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedAge,
                onValueChange = {},
                readOnly = true,
                label = { Text("Age") },
                trailingIcon = {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            painter = painterResource(id = R.drawable.dropdown_pet),
                            contentDescription = "Dropdown Icon"
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
            )

            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                ageOptions.forEach { age ->
                    DropdownMenuItem(
                        text = { Text(age) },
                        onClick = {
                            selectedAge = age
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Size",
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

        var petSize by remember { mutableStateOf(50f) }
        FilterSlider(
            value = petSize,
            onValueChange = { petSize = it },
            minValue = 1f,
            maxValue = 100f,
            startLabel = "Small",
            middleLabel = "Medium",
            endLabel = "Big"
        )
        Spacer(modifier = Modifier.height(150.dp))
        PetButton(text = "Next") {
            navController.navigate("photo") // Navigate to PetTakePhotoScreen
        }
    }
}

@Preview
@Composable
fun YourDogDetailsPreview() {
    val navController = rememberNavController() //Fix: Create navController
    YourDogDetails(navController = navController)
}

