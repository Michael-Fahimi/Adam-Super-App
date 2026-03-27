package com.madhan.feature_hotel.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.madhan.adamsuperapp.ui.theme.PrimaryColor
import com.madhan.adamsuperapp.ui.theme.SecondaryColor
import com.madhan.adamsuperapp.ui.theme.descriptionColor
import com.madhan.adamsuperapp.ui.theme.hotelTextColor
import com.madhan.core.ui.components.CustomSnackbar
import com.madhan.core.ui.components.PrimaryButton
import com.madhan.core.ui.components.SnackType
import com.madhan.feature_hotel.R
import com.madhan.feature_hotel.ui.widgets.CustomIconButton
import com.madhan.feature_hotel.ui.widgets.CustomTitleText
import com.madhan.feature_hotel.ui.widgets.IconTextRow
import com.madhan.feature_hotel.utils.routes.HOMESCREEN
import com.madhan.feature_hotel.utils.routes.HOTELDETAILSCREEN
import com.madhan.feature_hotel.utils.routes.ORDERSCREEN
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun OrderScreen(navController: NavController) {
    var showSnackbar by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }
    var snackType by remember { mutableStateOf(SnackType.SUCCESS) }

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ) {
            // **Header Section**
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(270.dp)
                    .background(PrimaryColor)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalAlignment = Alignment.Start
            ) {
                // **Header Row (Back, Title, Cancel)**
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomIconButton(
                        icon = painterResource(id = R.drawable.back_arrow),
                        onClick = {
                            navController.navigate(HOMESCREEN) {
                                popUpTo(ORDERSCREEN) { inclusive = true }
                            }
                        },
                        contentDescription = "Back arrow",
                        enabled = true,
                        contentColor = Color.White,
                        iconSize = 24.dp,
                    )
                    CustomTitleText(
                        modifier = Modifier.weight(1f),
                        text = "Order",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center
                    )
                    TextButton(onClick = {
                        navController.navigate(HOTELDETAILSCREEN) {
                            popUpTo(ORDERSCREEN) { inclusive = true }
                        }
                    }) {
                        Text(
                            text = "Cancel",
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }
                }
                Spacer(Modifier.height(20.dp))
                // Hotel Info Section
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text("Hotel", color = Color.White, fontSize = 14.sp)
                    Text(
                        "Resort Hotel",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(Modifier.height(20.dp))

                // Check-in and Check-out Info
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("Check In", color = Color.White, fontSize = 14.sp)
                        Text(
                            "20 March, Thu",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Box(
                        modifier = Modifier
                            .background(SecondaryColor, shape = RoundedCornerShape(50))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            "2N",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column {
                        Text("Check Out", color = Color.White, fontSize = 14.sp)
                        Text(
                            "22 March, Sat",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                Spacer(Modifier.height(20.dp))

                // Room & Guests Info
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("Room", color = Color.White, fontSize = 14.sp)
                        Text(
                            "1",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column {
                        Text("Adults", color = Color.White, fontSize = 14.sp)
                        Text(
                            "2",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column {
                        Text("Kids", color = Color.White, fontSize = 14.sp)
                        Text(
                            "1",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Spacer(Modifier.height(20.dp))

            // Order Summary
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                // Room & Image
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Junior Suite",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = hotelTextColor
                    )
                    Image(
                        painter = painterResource(id = R.drawable.hotelimg2),
                        contentDescription = "Hotel Image",
                        modifier = Modifier
                            .width(90.dp)
                            .height(50.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
                Spacer(Modifier.height(15.dp))

                // Pricing & Details
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            "Standard",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = hotelTextColor
                        )
                        TextButton(onClick = {}) {
                            Text(
                                "Remove",
                                color = PrimaryColor,
                                fontSize = 14.sp
                            )
                        }
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        IconTextRow(
                            icon = painterResource(id = R.drawable.moon),
                            text = "$150",
                            iconTint = descriptionColor
                        )
                        Spacer(Modifier.height(10.dp))
                        Text(
                            "x2",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = PrimaryColor
                        )
                    }
                }
                Spacer(Modifier.height(10.dp))
                HorizontalDivider(thickness = 0.5.dp, color = descriptionColor)
                Spacer(Modifier.height(10.dp))

                // Subtotal & Delivery Fees
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Subtotal", fontSize = 16.sp, color = hotelTextColor)
                        Text("$300.00", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(Modifier.height(15.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Delivery Fees", fontSize = 16.sp, color = hotelTextColor)
                        Text("$2.50", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
                Spacer(Modifier.height(20.dp))

                // **Total Amount**
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Total Amount",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = descriptionColor
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        "$302.50",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryColor
                    )
                }
                Spacer(Modifier.height(30.dp))

                // Confirm button
                PrimaryButton(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = {
                        snackbarMessage = "Order placed successfully!"
                        snackType = SnackType.SUCCESS
                        showSnackbar = true

                        coroutineScope.launch {
                            delay(2000L)
                            navController.navigate(HOMESCREEN) {
                                popUpTo(ORDERSCREEN) { inclusive = true }
                            }
                        }
                    },
                    text = "Place Order"
                )
            }
        }
    }
    // Show Custom Snackbar
    if (showSnackbar) {
        CustomSnackbar(
            message = snackbarMessage,
            snackType = snackType,
            onDismiss = { showSnackbar = false }
        )
        //Go to home
        navController.navigate(HOMESCREEN){
            popUpTo(ORDERSCREEN){inclusive=true}
      }
    }
}



@Preview(showBackground = true)
@Composable
fun OrderScreenPreview(){
    OrderScreen(
        navController = rememberNavController()
    )
}