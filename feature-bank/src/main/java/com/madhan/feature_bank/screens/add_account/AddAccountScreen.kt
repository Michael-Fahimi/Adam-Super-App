package com.madhan.feature_bank.screens.add_account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.madhan.feature_bank.components.BackTopBar


@Composable
fun AddAccountScreen(navController: NavController){
    Column (
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
    ) {
        BackTopBar("Add an account", navController)
        Spacer(modifier = Modifier.size(16.dp))
        CountryTabRow(navController)
    }
}

@Preview(showBackground = true)
@Composable
fun AddAccountScreenPreview(){
    AddAccountScreen(rememberNavController())
}