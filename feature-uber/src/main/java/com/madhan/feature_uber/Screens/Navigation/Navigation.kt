package com.madhan.feature_uber.Screens.Navigation


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import com.madhan.feature_uber.Screens.ui.RideInfo

import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.madhan.feature_uber.Screens.ui.DelayedTripDateTimeScreen
import com.madhan.feature_uber.Screens.ui.DestinationScreen
import com.madhan.feature_uber.Screens.ui.PermissionScreen
import com.madhan.feature_uber.Screens.ui.PickUpScreen
import com.madhan.feature_uber.Screens.ui.RideOptionsScreen
import com.madhan.feature_uber.Screens.ui.TransportScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.madhan.feature_uber.Screens.ui.LocationInfo
import com.madhan.feature_uber.Screens.ui.PaymentMethod
import com.madhan.feature_uber.Screens.ui.PaymentScreen
import com.madhan.feature_uber.Screens.ui.RideConfirmationScreen
import com.madhan.feature_uber.Screens.ui.RideRatingScreen
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


sealed class Screen(val route: String) {
    object Permission : Screen("uber/permission")
    object Transport : Screen("uber/transport")
    object SetPickup : Screen("uber/set_pickup")
    object SetDestination : Screen("uber/set_destination")
    object DelayedTrip : Screen("uber/delayed_trip")
    object RideOptions : Screen("uber/ride_options")
    object RideConfirmation : Screen("uber/ride_confirmation/{name}/{rating}/{car}/{carColor}/{time}/{price}")
    object Rating : Screen("uber/rating")
    object Payment : Screen("uber/payment")
}
@Composable
@RequiresApi(Build.VERSION_CODES.O)
fun SetupNavGraph(navController: NavHostController, onBackToHome: () -> Unit) {
    // Create remember variables to hold the location data
    var pickupLocation by remember { mutableStateOf<LocationInfo?>(null) }
    var destinationLocation by remember { mutableStateOf<LocationInfo?>(null) }

    NavHost(
        navController = navController,
        startDestination = Screen.Transport.route,
        route = "uber_graph"
    ) {
        composable(Screen.Transport.route) {
            TransportScreen(
                onLoginClick = { navController.navigate(Screen.Permission.route) },
                onBackHomeClick = { onBackToHome() }
            )
        }
        composable(Screen.Permission.route) {
            PermissionScreen(
                onBackClick = { navController.popBackStack() },
                onLetsGoClick = { navController.navigate(Screen.SetDestination.route) }
            )
        }
        composable(Screen.SetDestination.route) {
            DestinationScreen(
                onLocationSelected = { location ->
                    // Convert Location to LocationInfo
                    destinationLocation = LocationInfo(
                        name = location.name,
                        address = location.address,
                        city = "Johannesburg" // Assuming city is always Johannesburg
                    )
                    navController.navigate(Screen.SetPickup.route)
                },
                onBackClick = { navController.popBackStack() },
                onCalendar = { navController.navigate(Screen.DelayedTrip.route) }
            )
        }
        composable(Screen.SetPickup.route) {
            PickUpScreen(
                onProceed = { selected ->
                    // Convert Location to LocationInfo
                    pickupLocation = LocationInfo(
                        name = selected.name,
                        address = selected.address,
                        city = "Johannesburg" // Asg
                    )
                    navController.navigate(Screen.RideOptions.route)
                },
                onBackClick = { navController.popBackStack() },
                onCalendar = { navController.navigate(Screen.DelayedTrip.route) }
            )
        }
        composable(Screen.RideOptions.route) {
            RideOptionsScreen(
                onBackClick = { navController.popBackStack() },
                onDriverSelected = { driver ->
                    val encodedName = URLEncoder.encode(driver.name, StandardCharsets.UTF_8.toString())
                    val encodedCar = URLEncoder.encode(driver.car, StandardCharsets.UTF_8.toString())
                    val encodedCarColor = URLEncoder.encode(driver.carColor, StandardCharsets.UTF_8.toString())
                    navController.navigate(
                        "${Screen.RideConfirmation.route.split("/{")[0]}/$encodedName/${driver.rating}/$encodedCar/$encodedCarColor/${driver.estimatedTime}/${driver.estimatedPriceRange}"
                    )
                }
            )
        }
        composable(Screen.RideConfirmation.route) { backStackEntry ->
            val name = URLDecoder.decode(
                backStackEntry.arguments?.getString("name") ?: "",
                StandardCharsets.UTF_8.toString()
            )
            val rating = backStackEntry.arguments?.getString("rating")?.toDoubleOrNull() ?: 0.0
            val car = URLDecoder.decode(
                backStackEntry.arguments?.getString("car") ?: "",
                StandardCharsets.UTF_8.toString()
            )
            val carColor = URLDecoder.decode(
                backStackEntry.arguments?.getString("carColor") ?: "",
                StandardCharsets.UTF_8.toString()
            )
            val time = backStackEntry.arguments?.getString("time")?.toIntOrNull() ?: 0
            val price = backStackEntry.arguments?.getString("price") ?: ""

            // Use the saved location data
            val rideInfo = RideInfo(
                driverName = name,
                driverRating = rating,
                carModel = car,
                carColor = carColor,
                estimatedTime = time,
                estimatedPriceRange = price,
                // Use the saved location info or fallback to default
                destinationLocation = destinationLocation ?: LocationInfo("Work", "28 Broad Street", "Johannesburg"),
                pickupLocation = pickupLocation ?: LocationInfo("Home", "28 Orchard Road", "Johannesburg")
            )

            RideConfirmationScreen(
                rideInfo = rideInfo,
                onBackClick = { navController.popBackStack() },
                onCancelRide = { navController.popBackStack() },
                onOrderClick = { navController.navigate(Screen.Rating.route) },
                onSharePickup = { /* Handle share */ },
                onShareDestination = { /* Handle share */ },
                onEditPickup = { /* Handle edit */ },
                onEditDestination = { /* Handle edit */ }
            )
        }
        composable(Screen.Rating.route) {
            RideRatingScreen(
                onBackClick = { navController.popBackStack() },
                onProceed = { navController.navigate(Screen.Payment.route) }
            )
        }
        composable(Screen.Payment.route) {
            PaymentScreen(
                balance = 5523.26,
                paymentAmount = 9.50,
                paymentMethods = listOf(
                    PaymentMethod.Card(
                        cardNumber = "777777777777777777777",
                        cardHolderName = "Adam Adamian",
                        color = Color(0xFFDAA520)
                    ),
                    PaymentMethod.Cash
                ),
                onPayClick = {navController.navigate(Screen.Transport.route)},
                onHomeClick = { navController.navigate(Screen.Transport.route) }
            )
        }
    }
}
//@Composable
//fun RideConfirmationScreen(
//    rideInfo: RideInfo,
//    navController: NavHostController, // Add NavController as a parameter
//    onCancelRide: () -> Unit,
//    onOrderClick: () -> Unit,
//    onSharePickup: () -> Unit,
//    onShareDestination: () -> Unit,
//    onEditPickup: () -> Unit,
//    onEditDestination: () -> Unit
//) {
//    // Your UI implementation for the RideConfirmationScreen goes here
//    // You would call the lambda functions (e.g., onBackClick) based on user interactions
//}