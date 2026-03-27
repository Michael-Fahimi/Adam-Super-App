package com.madhan.feature_delivery.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.madhan.feature_delivery.R
import com.google.android.gms.maps.model.LatLng
import com.madhan.feature_delivery.ui.components.BottomOrangeButton
import com.madhan.feature_delivery.ui.components.CustomBackButton
import com.madhan.feature_delivery.ui.components.MapContent
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import android.location.Geocoder
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeliveryAddressScreen(navController: NavController) {
    val markerPosition = remember { mutableStateOf<LatLng?>(null) }
    val context = LocalContext.current
    var searchText by remember { mutableStateOf("") }
    var addresses by remember { mutableStateOf(listOf<Pair<String, LatLng>>()) }
    var selectedLatLng by remember { mutableStateOf<LatLng?>(null) }
    var selectedAddress by remember { mutableStateOf("") }

    // LatLngs within Atlanta
    val latLngs = listOf(
        LatLng(33.7756, -84.3963),   // Downtown Atlanta
        LatLng(33.7942, -84.3477),   // Buckhead
        LatLng(33.7333, -84.3780),   // Midtown
        LatLng(33.7627, -84.4225),   // West Midtown
        LatLng(33.8120, -84.3627)    // Lenox
    )

    LaunchedEffect(latLngs) {
        addresses = latLngs.mapNotNull { latLng ->
            getAddressFromLatLng(context, latLng)?.let { it to latLng }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = { Text("Enter delivery address") },
                navigationIcon = {
                    CustomBackButton {
                        navController.popBackStack()
                    }
                },
                actions = {
                    IconButton(onClick = {
                        val latlng = LatLng(33.7490, -84.3880)
                        markerPosition.value = latlng
                    }) {
                        Icon(painterResource(id = android.R.drawable.ic_menu_mylocation), contentDescription = "Current Location")
                    }
                }
            )

            TextField(
                value = if (selectedAddress.isNotEmpty()) selectedAddress else searchText,
                onValueChange = {
                    searchText = it
                    if (selectedAddress.isNotEmpty()) {
                        selectedAddress = ""
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                placeholder = { Text("Address") },
                trailingIcon = {
                    if (searchText.isNotEmpty() || selectedAddress.isNotEmpty()) {
                        IconButton(onClick = {
                            searchText = ""
                            selectedAddress = ""
                            markerPosition.value = null
                            selectedLatLng = null
                        }) {
                            Icon(Icons.Filled.Close, contentDescription = "Clear")
                        }
                    }
                }
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                items(addresses) { (address, latLng) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedLatLng = latLng
                                markerPosition.value = latLng
                                selectedAddress = address
                            }
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Filled.LocationOn, contentDescription = "Location", tint = Color.Red)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(address, fontSize = 16.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 316.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            MapContent(markerPosition = markerPosition.value)
        }

        if (selectedLatLng != null) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    BottomOrangeButton("Confirm") {
                        navController.navigate("delivery_men")
                    }
                }
            }
        }
    }
}

private fun getAddressFromLatLng(context: Context, latLng: LatLng): String? {
    val geocoder = Geocoder(context, Locale.getDefault())
    return try {
        val addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
        if (addresses != null && addresses.isNotEmpty()) {
            val address = addresses[0]
            address.getAddressLine(0)
        } else {
            null
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

@Preview(showBackground = true)
@Composable
fun DeliveryAddressScreenPreview() {
    DeliveryAddressScreen(rememberNavController())
}
