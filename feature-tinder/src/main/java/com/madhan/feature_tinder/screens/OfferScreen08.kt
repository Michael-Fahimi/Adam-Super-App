package com.madhan.feature_tinder.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.madhan.feature_tinder.R

@Composable
fun OfferScreen08(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.img_buy_boost_15),
            contentDescription = ""
        )
        Row {
            Column {
                Text(text = "1")
                Text(text = "Boosts")
                Text(text = "$3.99")
            }
            Column {
                Text(text = "5")
                Text(text = "Boosts")
                Text(text = "$5.99")
            }
            Column {
                Text(text = "10")
                Text(text = "Boosts")
                Text(text = "$6.99")
            }
        }
        Button(
            onClick = {}
        ) {
            Text(text = "Next")
        }
        Text(
            text = "No, thanks"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OfferScreen08Preview() {
    OfferScreen08()
}