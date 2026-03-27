package com.madhan.feature_bank.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.madhan.feature_bank.screens.IBankHomeScreen
import com.madhan.feature_bank.screens.IBankServices
import com.madhan.feature_bank.screens.accounts.AccountsScreen
import com.madhan.feature_bank.screens.add_account.AddAccountScreen
import com.madhan.feature_bank.screens.code.CodeScreen
import com.madhan.feature_bank.screens.congratulations.CongratulationScreen
import com.madhan.feature_bank.screens.credit_agricole.CreditAgricoleScreen
import com.madhan.feature_bank.screens.dashboard.DashboardScreen
import com.madhan.feature_bank.screens.loading.LoadingScreen
import com.madhan.feature_bank.screens.login.LoginScreen
import com.madhan.feature_bank.screens.transactions.TransactionScreen

fun NavGraphBuilder.bankNavGraph(
    navController: NavController
){
    navigation(
        startDestination = "ibank_home",
        route = "ibank"
    ) {

        composable("ibank_home") { IBankHomeScreen(navController) }
        composable("services") { IBankServices(navController) }
        composable("add_account") { AddAccountScreen(navController) }
        composable("accounts") { AccountsScreen(navController) }
        composable("dashboard") { DashboardScreen(navController) }
        composable("transactions") { TransactionScreen(navController) }
        composable("credit_agricole") { CreditAgricoleScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("code") { CodeScreen(navController) }
        composable("loading") { LoadingScreen(navController) }
        composable("congratulation") { CongratulationScreen(navController) }
    }
}