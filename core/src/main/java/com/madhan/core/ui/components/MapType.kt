package com.madhan.core.ui.components

import com.google.maps.android.compose.MapType

fun getNextMapType(currentType: MapType): MapType {
    return when (currentType) {
        MapType.NORMAL -> MapType.SATELLITE
        MapType.SATELLITE -> MapType.TERRAIN
        MapType.TERRAIN -> MapType.HYBRID
        else -> MapType.NORMAL
    }
}