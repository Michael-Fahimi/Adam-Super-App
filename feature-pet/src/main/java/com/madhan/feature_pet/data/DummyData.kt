package com.madhan.feature_pet.data

import com.madhan.feature_pet.R

data class PetCareProvider(
    val id: Int,
    val name: String,
    val location: String,
    val rating: Double,
    val pricePerHour: Double,
    val pricePerDay: Double,
    val distance: String,
    val imageResId: Int // Resource ID for provider's image
)

object DummyData {
    val petCareProviders = listOf(
        PetCareProvider(
            id = 1,
            name = "Jessy Jones",
            location = "Johannesburg",
            rating = 4.8,
            pricePerHour = 15.0,
            pricePerDay = 80.0,
            distance = "500 m",
            imageResId = R.drawable.provider1 // Ensure this drawable exists
        ),
        PetCareProvider(
            id = 2,
            name = "John Smith",
            location = "Cape Town",
            rating = 4.5,
            pricePerHour = 12.0,
            pricePerDay = 70.0,
            distance = "750 m",
            imageResId = R.drawable.provider2
        ),
        PetCareProvider(
            id = 3,
            name = "Emma Watson",
            location = "Durban",
            rating = 4.7,
            pricePerHour = 18.0,
            pricePerDay = 85.0,
            distance = "600 m",
            imageResId = R.drawable.provider3
        )
    )
}
