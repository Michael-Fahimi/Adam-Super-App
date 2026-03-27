package com.madhan.adamsuperapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.madhan.adamsuperapp.R
import com.madhan.adamsuperapp.auth.google.SigninWithGoogleViewModel
import com.madhan.adamsuperapp.navigation.Screen
import com.madhan.adamsuperapp.navigation.ServiceItem
import com.madhan.adamsuperapp.ui.theme.PrimaryColor
import com.madhan.adamsuperapp.utils.UiStatus
import com.madhan.core.ui.components.PrimaryButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val services = listOf(
        ServiceItem("Hotel", R.drawable.motel, Screen.Hotel.route),
        ServiceItem("Uber", R.drawable.uber, Screen.Uber.route),
        ServiceItem("Tinder", R.drawable.tinder, Screen.Tinder.route),
        ServiceItem("iBank", R.drawable.bank, Screen.IBank.route),
        ServiceItem("Delivery", R.drawable.delivery, Screen.Delivery.route),
        ServiceItem("Pet", R.drawable.pet, Screen.Pet.route),
    )

    //context
    val context = LocalContext.current

    // Get the ViewModel using hiltViewModel()
    val viewModel: SigninWithGoogleViewModel = hiltViewModel()

    // Observe the login state
    val logoutState by viewModel.logoutState.collectAsState()

    // UI elements based on login state
    when (logoutState) {
        is UiStatus.LOADING -> {}
        is UiStatus.SUCCESS -> {
            Toast.makeText(context, "Logout Successful", Toast.LENGTH_LONG).show()
            navController.navigate(Screen.SignIn.route) {
                popUpTo(Screen.Home.route) { inclusive = true }
            }
        }

        is UiStatus.ERROR -> {
            val errorMessage = (logoutState as UiStatus.ERROR).error
            Toast.makeText(context, "Login failed: $errorMessage", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.background(PrimaryColor),
                title = { Text("Super App", color = Color.White) },
                actions = {
                    IconButton(onClick = { viewModel.logOut() }) {
                        Icon(
                            modifier = Modifier.size(28.dp),
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Logout",
                            tint = PrimaryColor
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Welcome to Super App",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryColor
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Recent Services",
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray,
                fontSize = 16.sp,
            )
            Spacer(modifier = Modifier.height(24.dp))
            services.chunked(3).forEach { rowItems ->
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    rowItems.forEach { service ->
                        Box(
                            modifier = Modifier
                                .shadow(8.dp, RoundedCornerShape(12.dp))
                                .background(Color.White, RoundedCornerShape(12.dp))
                                .clickable { navController.navigate(service.route) }
                                .padding(1.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = service.icon),
                                    contentDescription = service.name,
                                    modifier = Modifier
                                        .size(64.dp)
                                        .padding(8.dp)
                                )
                                Text(text = service.name)
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}
