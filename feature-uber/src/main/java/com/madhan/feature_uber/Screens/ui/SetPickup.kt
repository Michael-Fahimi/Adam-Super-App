package com.madhan.feature_uber.Screens.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalBar
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Loupe
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.material.icons.filled.Work
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
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.madhan.feature_uber.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PickUpScreen(
    onProceed: (Location) -> Unit,
    onBackClick: () -> Unit,
    onCalendar: () -> Unit
)  {
    val presetLocations = listOf(
        Location("Home", "Johannesburg, 28 Orchard Road"),
        Location("Work", "Johannesburg, 20 Orchard Road"),
        Location("Gym", "Johannesburg, 15 Fitness Road"),
        Location("Bar", "Johannesburg, 10 Party Street")
    )

    var searchQuery by remember { mutableStateOf("") }
    var showSearchResults by remember { mutableStateOf(false) }
    var selectedLocation by remember { mutableStateOf<Location?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { /* Empty title to match the image */ },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .border(3.dp, Color.LightGray, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Set pick up location", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                    IconButton(onClick = { /* Handle calendar click */ }) {
                        Icon(Icons.Filled.CalendarMonth, contentDescription = "Calendar")
                    }
                }

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                        showSearchResults = it.isNotEmpty() // Show results only when there's input
                    },
                    placeholder = { Text("Search destination") },
                    leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
                    modifier = Modifier.fillMaxWidth(),
                )

                if (showSearchResults) {
                    LazyColumn {
                        items(presetLocations.filter { it.name.contains(searchQuery, ignoreCase = true) }) { location ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        selectedLocation = location
                                        searchQuery = location.name // Set the selected location name in the text field
                                        showSearchResults = false // Hide results
                                    }
                                    .padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Filled.LocationOn, contentDescription = "Location")
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(location.name, fontWeight = FontWeight.SemiBold)
                                    Text(location.address, fontSize = 12.sp)
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { selectedLocation = presetLocations[0] }) {
                        Icon(Icons.Filled.Home, contentDescription = "Home")
                    }
                    IconButton(onClick = { selectedLocation = presetLocations[1] }) {
                        Icon(Icons.Filled.Work, contentDescription = "Work")
                    }
                    IconButton(onClick = { selectedLocation = presetLocations[2] }) {
                        Icon(Icons.Filled.SportsGymnastics, contentDescription = "Gym")
                    }
                    IconButton(onClick = { selectedLocation = presetLocations[3] }) {
                        Icon(Icons.Filled.LocalBar, contentDescription = "Bar")
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                selectedLocation?.let { location ->
                    Row(verticalAlignment = Alignment.CenterVertically , modifier = Modifier.clickable {
                        onProceed(Location(location.name, location.address))
                    }) {
                        Icon(Icons.Filled.LocationOn, contentDescription = "Location")
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(location.name, fontWeight = FontWeight.SemiBold)
                            Text(location.address, fontSize = 12.sp)
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            val johannesburg = LatLng(-26.2041, 28.0473)
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(johannesburg, 13f)
            }

            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                onMapClick = { onCalendar() }
            ) {
                // Add a marker for the user's current location
                Marker(
                    state = MarkerState(position = johannesburg),
                    title = "Your Location"
                )

                // If a location is selected, add a marker for it
                selectedLocation?.let {
                    // In a real app, you would convert the address to coordinates
                    // This is just a placeholder
                    val locationPosition = LatLng(-26.2041, 28.0573) // Example offset
                    Marker(
                        state = MarkerState(position = locationPosition),
                        title = it.name
                    )
                }
            }
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(Color.Blue)
            )
            FloatingActionButton(
                onClick = { /* centralize back to user */ },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(Icons.Filled.Loupe, contentDescription = "Add")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PickUpScreenPreview() {
    val context = LocalContext.current
    CompositionLocalProvider(LocalContext provides context) {
        PickUpScreen(onProceed = {}, onBackClick = {}, onCalendar = {})

    }
}