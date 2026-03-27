package com.madhan.feature_delivery.ui.screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.madhan.feature_delivery.R
import com.madhan.feature_delivery.ui.components.HomeIcon
import com.madhan.core.ui.repo.getMarkerPositions
import com.madhan.core.ui.components.RenderMarkers
import com.madhan.core.ui.components.FetchCurrentLocationButton
import com.madhan.core.ui.components.getNextMapType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapSearchScreen(navController: NavController) {
    var mapType by remember { mutableStateOf(MapType.NORMAL) }
    val markerPositions = getMarkerPositions() // Fetch marker positions using your function
    var userLocation by remember { mutableStateOf<LatLng?>(null) } // State to hold the user's location
    val yourLatLng = LatLng(33.748, -84.39)  // Atlanta's coordinates


    Box(modifier = Modifier.fillMaxSize().padding(8.dp), contentAlignment = Alignment.TopCenter) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween
                ) {
                    HomeIcon(
                        onClick = { navController.navigate("delivery_home") },
                        modifier = Modifier.padding(8.dp),
                    )
                    IconButton(
                        onClick = { mapType = getNextMapType(mapType) },
                        modifier = Modifier
                            .size(28.dp) // Adjusted padding
                            .clip(CircleShape)
                            .background(Color.White) // Background to highlight the icon
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.menu),
                            contentDescription = "Switch Map Type",
                            modifier = Modifier.size(20.dp), // Reduced size
                            tint = Color.Black
                        )
                    }

                }

                // Column containing the map and the location button
                Column(modifier = Modifier.weight(1f)) {
                    val cameraPositionState = rememberCameraPositionState {
                        position = CameraPosition.fromLatLngZoom(markerPositions[0], 12f)
                    }

                    GoogleMap(
                        modifier = Modifier.fillMaxSize(),
                        cameraPositionState = cameraPositionState,
                        properties = MapProperties(mapType = mapType)
                    ) {
                        RenderMarkers(markerPositions) // Render the predefined markers

                        // If user location is available, render the marker for it
                        userLocation?.let {
                            Marker(
                                state = MarkerState(position = yourLatLng),  // Use the LatLng value here
                                title = "Your Location",
                                snippet = "You are here"
                            )
                        }
                    }

                    // Location button to fetch current location
                    FetchCurrentLocationButton(
                        context = LocalContext.current,
                        onLocationReceived = { location ->
                            userLocation = location// Update user location on click
                            // Move the camera to the user's location
                            cameraPositionState.position = CameraPosition.fromLatLngZoom(location, 12f)
                        }
                    )
                }

                // LazyRow for drivers (unchanged)
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(getDriverList()) { driver ->
                        DriverCard(driver, navController) {
                            navController.navigate("order_details")
                        }
                    }
                }
            }
        }
    }
}

fun getDriverList() = listOf(
    Data("Jenny Jones", 4.8f, 4.5f, R.drawable.delivery_guy),
    Data("Sacha Down", 4.6f, 3.8f, R.drawable.delivery_guy2),
    Data("Johnathan", 4.3f, 3.8f, R.drawable.delivery_guy3),
    Data("William", 4.7f, 3.8f, R.drawable.delivery_guy4)
)

data class Data(val name: String, val rating: Float, val distance: Float, val imageRes: Int)

@Composable
fun DriverCard(driver: Data, navController: NavController, onClick: () -> Unit) {
    Card(
        modifier = Modifier.width(160.dp).padding(4.dp).clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = driver.imageRes),
                contentDescription = driver.name,
                modifier = Modifier.size(80.dp).clip(CircleShape)
            )
            Text(text = driver.name, fontWeight = FontWeight.Bold)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painterResource(id = R.drawable.star),
                    contentDescription = "Rating",
                    tint = Color.Yellow,
                    modifier = Modifier.size(16.dp)
                )
                Text(text = "${driver.rating}")
            }
            Text(text = "${driver.distance} Mile Nearby")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun AfricarScreenPreview() {
    MapSearchScreen(rememberNavController())
}
