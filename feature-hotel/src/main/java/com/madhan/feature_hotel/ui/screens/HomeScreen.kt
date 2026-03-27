package com.madhan.feature_hotel.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.madhan.adamsuperapp.ui.theme.PrimaryColor
import com.madhan.adamsuperapp.ui.theme.hotelTextColor
import com.madhan.core.ui.components.CustomDataCard
import com.madhan.feature_hotel.R
import com.madhan.feature_hotel.data.DummyData
import com.madhan.feature_hotel.data.vm.FavoriteViewModel
import com.madhan.feature_hotel.ui.widgets.*
import com.madhan.feature_hotel.utils.routes.FAVORITESCREEN
import com.madhan.feature_hotel.utils.routes.FILTERSCREEN
import com.madhan.feature_hotel.utils.routes.HOTELDETAILSCREEN
import com.madhan.feature_hotel.utils.routes.ORDERSSCREEN
import com.madhan.feature_hotel.utils.routes.PLACESCREEN

@Composable
fun HomeScreen(navController: NavController,viewModel: FavoriteViewModel = viewModel()) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        // State to control HotelSearchCard visibility
        var showSearchCard by remember { mutableStateOf(false) }
        //state to control favorite list
        val favoriteHotels by viewModel.favoriteHotels.collectAsState()// Observe state

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .clickable(enabled = showSearchCard) { showSearchCard = false }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                userScrollEnabled = true
            ) {
                // Background Image with Home Icon
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(261.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.hotelimg1), // Background image
                            contentDescription = "Hotel Background",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                   //Back button
                        IconButton(
                            onClick = { showSearchCard = !showSearchCard },
                            modifier = Modifier
                                .padding(16.dp)
                                .size(40.dp)
                                .background( if(!showSearchCard) Color.Black.copy(alpha = 0.5f) else PrimaryColor, CircleShape) // Semi-transparent background
                        ) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = Icons.Filled.Home,
                                contentDescription = "Back",
                                tint = if (!showSearchCard) Color.White else PrimaryColor
                            )
                        }
                        // Show Hotel Search Card only when showSearchCard is true
                        if (showSearchCard) {
                                HotelSearchCard(
                                    modifier = Modifier,
                                    onClick = { navController.navigate(PLACESCREEN) } //Opens map
                                )
                        }
                    }
                }
                // Favorites and Orders Row
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(PrimaryColor),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        IconTextRow(
                            modifier = Modifier.clickable {
                                navController.navigate(FAVORITESCREEN)
                            },
                            icon = painterResource(id = R.drawable.heart),
                            text = "Favorites",
                            textSize = 16.sp,
                            textColor = Color.White,
                            iconSize = 24.dp,
                            iconTint = Color.White
                        )

                        IconTextRow(
                            modifier = Modifier
                                .clickable { navController.navigate(ORDERSSCREEN) },
                            icon = painterResource(id = R.drawable.file),
                            text = "Orders",
                            textSize = 16.sp,
                            iconSize = 24.dp,
                            textColor = Color.White,
                            iconTint = Color.White,
                        )
                    }
                }
                // Spacer after icons
                item { Spacer(modifier = Modifier.height(16.dp)) }
                // Recommended Hotels Title
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CustomTitleText(
                            text = "Recommended Hotels",
                            fontSize = 18.sp,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.weight(1f)
                        )
                        //Filter button
                        IconButton(
                            onClick = {navController.navigate(FILTERSCREEN)}, //Go to filter screen
                            enabled = true,
                            colors = IconButtonDefaults.filledIconButtonColors (
                               containerColor =  Color.Transparent,
                                contentColor = PrimaryColor
                            )
                        ) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(R.drawable.options),
                                contentDescription = "filter",
                                tint =  PrimaryColor
                            )
                        }
                    }
                }

                // Recommended Hotels List
                items(DummyData.hotelList) { hotel ->
                    val isFavorite = favoriteHotels.contains(hotel)
                    CustomDataCard(
                        modifier = Modifier.clickable { navController.navigate(HOTELDETAILSCREEN) },
                        backgroundImage = painterResource(id = hotel.imageResId),
                        hotelType = hotel.hotelType,
                        hotelLocation = hotel.hotelLocation,
                        hotelRating = hotel.hotelRating,
                        hotelDistance = hotel.hotelDistance,
                        hotelPrice = hotel.hotelPrice,
                        isFavorite = isFavorite,
                        onFavoriteClick = { viewModel.toggleFavorite(hotel) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}