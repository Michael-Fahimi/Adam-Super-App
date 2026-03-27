package com.madhan.feature_hotel.data.model

import com.google.android.gms.maps.model.LatLng

data class HotelLocation(
    val name: String,
    val location: LatLng,
    val rating: Double,
    val imageRes: Int
)
