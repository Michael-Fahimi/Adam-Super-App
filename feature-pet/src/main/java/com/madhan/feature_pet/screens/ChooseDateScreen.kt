package com.madhan.feature_pet.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.madhan.core.ui.components.CommonCalendar
import com.madhan.core.ui.components.CustomButton
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChooseDateScreen(navController: NavHostController, onDateSelected: (String, String) -> Unit) {
    var startDate by remember { mutableStateOf<LocalDate?>(null) }
    var endDate by remember { mutableStateOf<LocalDate?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Common Calendar
        CommonCalendar(
            startDate = startDate,
            endDate = endDate,
            onDateSelected = { selectedDate ->
                if (startDate == null || (startDate != null && endDate != null)) {
                    startDate = selectedDate
                    endDate = null
                } else if (selectedDate > startDate!!) {
                    endDate = selectedDate
                }
            },
            onBackPress = { navController.popBackStack() } //Navigate back on button press
        )

        Spacer(modifier = Modifier.weight(1f))

        // Next Button
        CustomButton(
            onClick = {
                if (startDate != null && endDate != null) {
                    val startDateStr = startDate.toString()
                    val endDateStr = endDate.toString()

                    // ✅ Debug: Print selected dates
                    println("Navigating with startDate: $startDateStr, endDate: $endDateStr")

                    // ✅ Store dates safely
                    navController.currentBackStackEntry?.savedStateHandle?.set("startDate", startDateStr)
                    navController.currentBackStackEntry?.savedStateHandle?.set("endDate", endDateStr)

                    // ✅ Navigate to PetCarePersonListScreen
                    navController.navigate("pet_care_list")
                } else {
                    println("Start date or end date is null!") // Debugging log
                }
            },
            buttonText = "Next"
        )
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun ChooseDateScreenPreview() {
    val navController = rememberNavController()
    ChooseDateScreen(navController) { _, _ -> }
}
