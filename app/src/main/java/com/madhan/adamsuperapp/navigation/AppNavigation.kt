package com.madhan.adamsuperapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.madhan.adamsuperapp.ui.screens.HomeScreen
import com.madhan.adamsuperapp.ui.screens.SignInScreen
import com.madhan.adamsuperapp.ui.screens.SignUpScreen
import com.madhan.feature_bank.navigation.bankNavGraph
import com.madhan.feature_bank.navigation.tinderNavGraph
import com.madhan.feature_delivery.navigation.deliveryNavGraph
import com.madhan.feature_hotel.data.vm.FavoriteViewModel
import com.madhan.feature_hotel.navigation.hotelNavGraph
import com.madhan.feature_uber.Screens.Navigation.SetupNavGraph
import com.madhan.feature_pet.navigation.petNavGraph

@Composable
fun AppNavigation(navController: NavHostController) {

    val isLoggedIn = Firebase.auth.currentUser

    // Create the FavoriteViewModel for Favorites
    val favoriteViewModel: FavoriteViewModel = viewModel()
    NavHost(
        navController,
        startDestination = if (isLoggedIn == null) Screen.SignIn.route else Screen.Home.route,
        route = "root"
    ) {
        composable(Screen.SignIn.route) { SignInScreen(navController) }
        composable(Screen.SignUp.route) {
            SignUpScreen(
                navController,
                onNavigateToSignIn = { navController.navigate(Screen.SignIn.route) })
        }
        composable(Screen.Home.route) { HomeScreen(navController) }
        navigation(
            startDestination = "uber_main",
            route = Screen.Uber.route
        ) {
            composable("uber_main") {
                // Create a nested NavController specifically for the Uber feature
                val uberNavController = rememberNavController()

                // Pass a callback to handle back navigation to home
                SetupNavGraph(
                    navController = uberNavController,
                    onBackToHome = {
                        // Navigate to the home screen
                        navController.navigate(Screen.Home.route) {
                            // Clear the back stack up to home
                            popUpTo("root") {
                                inclusive = false
                            }
                        }
                    }
                )
            }
        }
        deliveryNavGraph(navController) // Delivery NavGraph
        petNavGraph(navController) // Pet NavGraph
        tinderNavGraph(navController) // Tinder NavGraph
        bankNavGraph(navController) // IBank NavGraph
        hotelNavGraph(navController, favoriteViewModel) // Hotel NavGraph
    }
}
