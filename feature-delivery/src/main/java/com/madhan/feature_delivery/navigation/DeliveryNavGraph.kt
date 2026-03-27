package com.madhan.feature_delivery.navigation

import OrderDetailsScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.madhan.feature_delivery.ui.screens.DeliveryAddressScreen
import com.madhan.feature_delivery.ui.screens.DeliveryHomeScreen
import com.madhan.feature_delivery.ui.screens.DeliveryMenInfo
import com.madhan.feature_delivery.ui.screens.FilterScreen
import com.madhan.feature_delivery.ui.screens.MapSearchScreen
import com.madhan.feature_delivery.ui.screens.OrderSummaryScreen
import com.madhan.feature_delivery.ui.screens.ParcelScreen
import com.madhan.feature_delivery.ui.screens.ScheduleScreen

fun NavGraphBuilder.deliveryNavGraph(
    navController: NavController
){
    navigation(
        startDestination = "delivery_home",
        route = "delivery"
    ) {

        composable("delivery_home") { DeliveryHomeScreen(navController) }
        composable("parcel") { ParcelScreen(navController) }
        composable("delivery_address") {DeliveryAddressScreen(navController) }
        composable("delivery_men") { DeliveryMenInfo(navController) }
        composable("filter") { FilterScreen(navController) }
        //composable("schedule") { ScheduleScreen(navController) }
        composable("map_search") { MapSearchScreen(navController) }
        composable("order_details") { OrderDetailsScreen(navController) }
        composable("order_summary") { OrderSummaryScreen(navController) }

    }
}
