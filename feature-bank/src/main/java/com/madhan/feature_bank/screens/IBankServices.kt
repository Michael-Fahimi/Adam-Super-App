package com.madhan.feature_bank.screens

import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.madhan.adamsuperapp.ui.theme.PrimaryColor
import com.madhan.feature_bank.components.BackTopBar

@Composable
fun IBankServices(navController: NavController) {
    val services = listOf(
        "Add Account" to "add_account",
        "Accounts" to "accounts",
        "Dashboard" to "dashboard",
        "Transactions" to "transactions"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        BackTopBar("Services", navController) {
            navController.navigate("ibank_home") {
                popUpTo(0) { inclusive = true }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Our Services",
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold,
            color = PrimaryColor
        )

        Spacer(modifier = Modifier.height(32.dp))

        LazyColumn {
            items(services) { (serviceName, route) ->
                ServiceItem(serviceName, route, navController)
            }
        }
    }
}

@Composable
fun ServiceItem(serviceName: String, route: String, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(4.dp, RoundedCornerShape(8.dp))
            .background(Color.White, RoundedCornerShape(8.dp))
            .clickable { navController.navigate(route) }
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "•", fontSize = 18.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = serviceName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun IBankServicesPreview() {
    IBankServices(rememberNavController())
}