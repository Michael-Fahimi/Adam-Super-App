package com.madhan.feature_bank.screens.credit_agricole

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.madhan.feature_bank.R
import com.madhan.feature_bank.components.BackTopBar
import com.madhan.feature_bank.components.BankTab
import com.madhan.feature_bank.data.CountryData

@Composable
fun CreditAgricoleScreen(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
    ) {
        BackTopBar("Credit Agricole", navController)

        Spacer(modifier = Modifier.size(30.dp))
        BankTab(CountryData(R.drawable.agricole_bank, "Credit Agricole Provence", "login"), navController)

        Spacer(modifier = Modifier.size(24.dp))
        BankTab(CountryData(R.drawable.agricole_bank, "Credit Agricole PACA", "login"), navController)
    }
}

@Preview(showBackground = true)
@Composable
fun CreditAgricoleScreenPreview() {
    CreditAgricoleScreen(rememberNavController())
}