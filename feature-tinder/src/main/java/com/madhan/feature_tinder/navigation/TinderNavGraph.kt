package com.madhan.feature_bank.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.madhan.feature_tinder.TinderRoute
import com.madhan.feature_tinder.screens.AddProfileScreen03
import com.madhan.feature_tinder.screens.EnableLocationScreen04
import com.madhan.feature_tinder.screens.PictureChoiceScreen01
import com.madhan.feature_tinder.screens.ProfileScreen07
import com.madhan.feature_tinder.screens.TakePhotoScreen02
import com.madhan.feature_tinder.screens.TinderDisplayScreen06
import com.madhan.feature_tinder.screens.TinderScreen00
import com.madhan.feature_tinder.screens.TutorialScreen05
import com.madhan.feature_tinder.screens.UpdateProfileScreen03
import com.madhan.feature_tinder.viewmodel.ProfileViewModel


fun NavGraphBuilder.tinderNavGraph(
    navController: NavController
){
    navigation(
        startDestination = TinderRoute.TinderScreen.route,
        route = "tinder",
    ) {
        composable(route = TinderRoute.TinderScreen.route) { TinderScreen00(navController = navController) }
        composable(route = TinderRoute.PictureChoice.route) { PictureChoiceScreen01(navController = navController) }
        composable(route = TinderRoute.TakePhoto.route) { TakePhotoScreen02(navController = navController) }
        composable(route = TinderRoute.AddProfile.route) { AddProfileScreen03(navController = navController) }
        composable(route = TinderRoute.EnableLocation.route) { EnableLocationScreen04(navController = navController) }
        composable(route = TinderRoute.TutorialScreen.route) { TutorialScreen05(navController = navController) }
        var viewModel: ProfileViewModel? = null
        composable(route = TinderRoute.TinderDisplayScreen.route) {
           viewModel = viewModel ?: viewModel()
            TinderDisplayScreen06(
                navController = navController,
                viewModel = viewModel!! // Pass the shared viewModel
            )
        }
        composable("profile_screen") {
            viewModel = viewModel ?: viewModel()
            ProfileScreen07(
                navController = navController,
                viewModel = viewModel!! // Pass the shared viewModel
            ) }
    }
}
