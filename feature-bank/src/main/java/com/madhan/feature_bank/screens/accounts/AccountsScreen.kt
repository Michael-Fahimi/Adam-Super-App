package com.madhan.feature_bank.screens.accounts

import RightColumnTab
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.madhan.feature_bank.components.HomeTopBar

@Composable
fun AccountsScreen(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
    ) {
        HomeTopBar("Accounts", navController)
        RightColumnTab(
            allAccountsTab = { AllAccountsTab() },
            currentAccountsTab = {CurrentAccountsTabs()},
            savingsAccountTab = {SavingsAccountsTab()},
            creditAccountsTab = {CreditAccountsTab()}
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AccountsScreenPreview(){
    AccountsScreen(rememberNavController())
}