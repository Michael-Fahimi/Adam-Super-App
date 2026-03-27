package com.madhan.feature_pet.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.NavHost
import com.madhan.feature_pet.screens.*

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.petNavGraph(navController: NavHostController) {
    navigation(
        startDestination = "start",
        route = "pet"  // This allows the main app to recognize this module
    ) {
        composable("start") { StartScreen(navController) }
        composable("your_dog_details") { YourDogDetails(navController) }
        composable("filter") { FilterScreen(navController) }
        composable("photo") { PetTakePhotoScreen(navController) }
        composable("dog_list") { DogListScreen(navController) }
        composable("choose_date") {
            ChooseDateScreen(navController) { startDate, endDate ->
                navController.previousBackStackEntry?.savedStateHandle?.set("startDate", startDate)
                navController.previousBackStackEntry?.savedStateHandle?.set("endDate", endDate)
                navController.popBackStack()
            }
        }
        composable("pet_care_list") {
            val startDate = navController.currentBackStackEntry?.savedStateHandle?.get<String>("startDate") ?: "Choose Dates"
            val endDate = navController.currentBackStackEntry?.savedStateHandle?.get<String>("endDate") ?: ""
            PetCarePersonListScreen(
                navController
            )
        }
        composable("order/{guardName}/{startDate}/{endDate}") { backStackEntry ->
            val guardName = backStackEntry.arguments?.getString("guardName") ?: "Unknown"
            val startDate = backStackEntry.arguments?.getString("startDate") ?: "Unknown"
            val endDate = backStackEntry.arguments?.getString("endDate") ?: "Unknown"
            OrderScreen(navController, guardName, "Kobe", startDate, endDate)
        }
        composable("appointment_success") { AppointmentSuccessfulScreen(navController) }

    }
}
