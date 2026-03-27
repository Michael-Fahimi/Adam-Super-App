package com.madhan.feature_bank.screens.loading

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.madhan.feature_bank.R
import kotlinx.coroutines.delay

@Composable
fun LoadingScreen(navController: NavController) {

    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate("congratulation") {
            popUpTo("add_account") { inclusive = true } // Remove Loading Screen from backstack
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(64.dp))

        Image(
            painter = painterResource(id = R.drawable.loading_screen_image),
            contentDescription = "loading screen image",
            modifier = Modifier
                .width(284.dp)
                .height(218.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Loading...",
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Please wait while we synchronize your data",
            color = Color.Gray,
            fontSize = 16.sp,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    LoadingScreen(rememberNavController())
}