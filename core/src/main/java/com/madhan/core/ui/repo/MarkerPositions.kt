package com.madhan.core.ui.repo

import com.google.android.gms.maps.model.LatLng

    fun getMarkerPositions(): List<LatLng> {
        return listOf(
            LatLng(33.7756, -84.3963),   // Downtown Atlanta
            LatLng(33.7942, -84.3477),   // Buckhead
            LatLng(33.7333, -84.3780),   // Midtown
            LatLng(33.7627, -84.4225),   // West Midtown
            LatLng(33.8120, -84.3627)    // Lenox
        )
    }
