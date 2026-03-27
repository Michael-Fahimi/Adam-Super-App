package com.madhan.feature_pet.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.madhan.core.ui.components.CustomDataCard
import com.madhan.core.ui.components.CustomSearchCard
import com.madhan.feature_pet.R
import com.madhan.feature_pet.data.DummyData

@Composable
fun PetCarePersonListScreen(navController: NavHostController) {
    val startDate = remember {
        navController.previousBackStackEntry?.savedStateHandle?.get<String>("startDate") ?: "Choose Dates"
    }
    val endDate = remember {
        navController.previousBackStackEntry?.savedStateHandle?.get<String>("endDate") ?: ""
    }

    println("Received startDate: $startDate, endDate: $endDate")

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        var showSearchCard by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .clickable(enabled = showSearchCard) { showSearchCard = false }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                userScrollEnabled = true
            ) {
                // Background Image with Home Button
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(261.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.pet_background),
                            contentDescription = "Pet Background",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        IconButton(
                            onClick = { showSearchCard = !showSearchCard },
                            modifier = Modifier.align(Alignment.TopStart),
                            colors = IconButtonDefaults.filledIconButtonColors(
                                containerColor = if (!showSearchCard) Color.White else Color(0xFFFF8C00)
                            )
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_home),
                                contentDescription = "Home Icon",
                                tint = if (!showSearchCard) Color(0xFFFF8C00) else Color.White
                            )
                        }
                        if (showSearchCard) {
                            CustomSearchCard(
                                modifier = Modifier,
                                onClick = { navController.navigate("choose_location") },
                                onChooseDatesClick = {navController.navigate("choose_date") },
                                startDate = startDate,
                                endDate = endDate
                            )
                        }
                    }
                }

                // Favorites and Orders Section
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(Color(0xFFFF8C00)),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_heart),
                                contentDescription = "Favorites",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "Favorites", fontSize = 16.sp, color = Color.White)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.file),
                                contentDescription = "Orders",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "Orders", fontSize = 16.sp, color = Color.White)
                        }
                    }
                }

                // Title & Filter Button
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Pet Care Providers",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.weight(1f)
                        )
                        IconButton(
                            onClick = { navController.navigate("filter") },
                            colors = IconButtonDefaults.filledIconButtonColors(
                                containerColor = Color.Transparent,
                                contentColor = Color(0xFFFF8C00)
                            )
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.filter),
                                contentDescription = "Filter",
                                tint = Color(0xFFFF8C00)
                            )
                        }
                    }
                }

                // List of Pet Care Providers
                items(DummyData.petCareProviders) { provider ->
                    CustomDataCard(
                        modifier = Modifier.clickable {
                            navController.navigate("order/${provider.name}/$startDate/$endDate")
                        },
                        backgroundImage = painterResource(id = provider.imageResId),
                        hotelType = provider.name, // Name
                        hotelLocation = provider.location, // Location
                        hotelRating = provider.rating.toString(), // Rating
                        hotelDistance = provider.distance, // Distance
                        hotelPrice = "$${provider.pricePerDay}", // PricePerDay
                        isFavorite = false,
                        onFavoriteClick = {}
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PetCarePersonListScreenPreview() {
    val navController = rememberNavController()
    PetCarePersonListScreen(navController = navController)
}
