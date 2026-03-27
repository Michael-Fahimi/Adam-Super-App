package com.madhan.core.ui.components

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.madhan.core.R

@Composable
fun FetchCurrentLocationButton(context: Context, onLocationReceived: (LatLng) -> Unit) {
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    FloatingActionButton(
        onClick = {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                    location?.let {
                        val hardCodedLocation = LatLng(33.748699, -84.39249)
                        onLocationReceived(hardCodedLocation)
                    }
                }
            } else {
                Log.e("Location", "Permission not granted")
            }
        },
        modifier = Modifier
            .padding(16.dp)
            .size(60.dp)

    ) {
        Icon(painter = painterResource(id = R.drawable.geo_location), contentDescription = "My Location",tint = Color(0xFFFF8000) )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewFetchCurrentLocationButton() {
    // Use an empty context for preview purposes
    val context = LocalContext.current

    // Dummy location received callback
    FetchCurrentLocationButton(context) { latLng ->
        // This block will handle the location update
        Log.d("Location", "New location: $latLng")
    }
}
