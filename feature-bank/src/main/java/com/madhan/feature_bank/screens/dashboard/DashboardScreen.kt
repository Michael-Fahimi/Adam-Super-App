package com.madhan.feature_bank.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.madhan.feature_bank.components.HomeTopBar


@Composable
fun DashboardScreen(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = Color.White)
            .padding(top = 16.dp)
    ) {
        HomeTopBar("Dashboard", navController)
        LeftColumnTab(
            allAccountsTab = { AllAccountDashboard() },
            currentAccountsTab = { CurrentAccountDashboard() },
            savingsAccountTab = { SavingsAccountDashboard() },
            creditAccountsTab = { CreditAccountDashboard() }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview(){
    DashboardScreen(rememberNavController())
}