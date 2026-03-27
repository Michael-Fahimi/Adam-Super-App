package com.madhan.feature_bank.screens.congratulations

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.madhan.core.ui.components.CustomButton
import com.madhan.feature_bank.R


@Composable
fun CongratulationScreen(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(64.dp))

        Image(
            painter = painterResource(id = R.drawable.congratulation_screen_image),
            contentDescription = "loading screen image",
            modifier = Modifier
                .width(284.dp)
                .height(218.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Congratulation",
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Your bank has been added successfully.",
            color = Color.Gray,
            fontSize = 16.sp,
        )

        Spacer(modifier = Modifier.weight(1f))

        CustomButton("Next") {
            navController.navigate("services") {
                popUpTo("ibank_home") {
                    inclusive = true
                } // Remove Loading Screen from backstack
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CongratulationScreenPreview() {
    CongratulationScreen(rememberNavController())
}