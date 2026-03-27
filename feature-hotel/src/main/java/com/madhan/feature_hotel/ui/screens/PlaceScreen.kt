package com.madhan.feature_hotel.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.*
import com.google.maps.android.compose.*
import com.madhan.adamsuperapp.ui.theme.PrimaryColor
import com.madhan.feature_hotel.R
import com.madhan.feature_hotel.data.DummyData.hotelLocationList
import com.madhan.feature_hotel.ui.widgets.HotelCard
import com.madhan.feature_hotel.utils.routes.HOMESCREEN
import com.madhan.feature_hotel.utils.routes.HOTELDETAILSCREEN
import com.madhan.feature_hotel.utils.routes.ORDERSCREEN
import com.madhan.feature_hotel.utils.routes.PLACESCREEN

@Composable
fun PlaceScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(37.7749, -122.4194), 12f) // Default: San Francisco
    }

    //search functionality
    val filteredHotels = remember(searchQuery) {
        hotelLocationList.filter {
            it.name.contains(searchQuery, ignoreCase = true) ||
                    it.name.contains(searchQuery, ignoreCase = true)
        }
    }

    //Move camera to first location
    LaunchedEffect(searchQuery) {
        filteredHotels.firstOrNull()?.let { hotel ->
            cameraPositionState.move(CameraUpdateFactory.newLatLng(hotel.location))
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            // Google Map
            GoogleMap(
                modifier = Modifier
                    .fillMaxSize(),
                   // .padding(top = 10.dp),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(isMyLocationEnabled = false)
            ) {
                //Searched location marker
                filteredHotels.forEach { hotel ->
                    Marker(
                        state = MarkerState(position = hotel.location),
                        title = hotel.name,
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
                    )
                }
            }
            // Floating Button
            Column(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.End
            ) {
                // Search Bar
                Box(modifier = Modifier) {
                    TextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text("Search location / name / country") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .background(Color.White, shape = RoundedCornerShape(10.dp))
                            .shadow(8.dp),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.search),
                                contentDescription = "Search Icon"
                            )
                        }
                    )
                }
                Column(modifier = Modifier.padding(end=10.dp),
                    ) {
                    //close map
                    FloatingActionButton(
                        onClick = {
                            navController.navigate(HOMESCREEN){
                                popUpTo(PLACESCREEN){inclusive=true}
                            }
                                  },
                        containerColor = Color.White
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(24.dp),
                            painter = painterResource(id = R.drawable.close),
                            contentDescription = "close map",
                            tint = PrimaryColor
                        )
                    }
                }
            }
            // Bottom Hotel List
            LazyRow(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                items(filteredHotels) { hotel ->
                    HotelCard(
                        hotel=hotel,
                        onClick = {
                            navController.navigate(HOTELDETAILSCREEN){
                                popUpTo(PLACESCREEN){inclusive=true}
                            }
                        }
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PlaceScreenPreview(){
    val navController = rememberNavController()
    PlaceScreen(navController = navController)
}