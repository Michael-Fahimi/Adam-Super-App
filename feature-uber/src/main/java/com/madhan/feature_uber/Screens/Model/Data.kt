package com.madhan.feature_uber.Screens.Model

data class Driver(
    val id: String,
    val name: String,
    val rating: Double,
    val carModel: String,
    val carNumber: String,
    // For static data, you might include a starting location for demonstration
    val latitude: Double,
    val longitude: Double
)

data class PresetLocation(
    val name: String,
    val latitude: Double,
    val longitude: Double
)

data class CreditCard(
    val cardNumber: String,
    val expiryDate: String,
    val cvv: String,
    val cardHolderName: String
)