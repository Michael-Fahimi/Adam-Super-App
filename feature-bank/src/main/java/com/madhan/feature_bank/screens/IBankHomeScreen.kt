package com.madhan.feature_bank.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.madhan.core.ui.components.CustomButton
import com.madhan.feature_bank.R
import com.madhan.feature_bank.components.BackTopBar

@Composable
fun IBankHomeScreen(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        BackTopBar("", navController) {
            navController.navigate("home")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.home_welcome_image),
            contentDescription = "home image",
            modifier = Modifier
                .width(299.dp)
                .height(221.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Bank",
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis lobortis sit amet odio in egestas. Pellen tesque ultricies justo.",
            color = Color.LightGray,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        CustomButton("Let's go") {
            navController.navigate("services")
        }
    }

}


@Preview(showBackground = true)
@Composable
fun IBankHomeScreenPreview() {
    IBankHomeScreen(rememberNavController())
}