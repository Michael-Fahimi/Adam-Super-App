package com.madhan.adamsuperapp.navigation

sealed class Screen(val route: String) {
    object SignIn : Screen("sign_in")
    object SignUp : Screen("sign_up")
    object Home : Screen("home")
    object Hotel : Screen("hotel")
    object Uber : Screen("uber")
    object Tinder : Screen("tinder")
    object Delivery : Screen("delivery")
    object IBank : Screen("ibank")
    object Pet : Screen("pet")

}
