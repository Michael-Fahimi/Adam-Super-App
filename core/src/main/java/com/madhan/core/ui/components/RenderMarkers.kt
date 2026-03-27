package com.madhan.core.ui.components

import androidx.compose.runtime.Composable
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState

@Composable
fun RenderMarkers(markerPositions: List<LatLng>) {
    markerPositions.forEach { position ->
        // Correctly using MarkerState with the position
        Marker(
            state = MarkerState(position = position),
            title = "Marker Title",  // Optional: Set a title if needed
            snippet = "Marker Snippet"  // Optional: Set a snippet if needed
        )
    }
}
