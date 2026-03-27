package com.madhan.feature_bank.data

import com.madhan.feature_bank.R

object DummyData {
    val franceData = listOf(
        CountryData(R.drawable.bfb_bank, "B for Bank", "credit_agricole"),
        CountryData(R.drawable.bnp_bank, "BNP Paribas", "credit_agricole"),
        CountryData(R.drawable.caisee_bank, "Caisse d'epargne", "credit_agricole"),
        CountryData(R.drawable.agricole_bank, "Credit Agricole", "credit_agricole"),
        CountryData(R.drawable.societe_bank, "Societe Generale", "credit_agricole"),
    )
}


data class CountryData(
    val drawableId: Int,
    val title: String,
    val route: String
)