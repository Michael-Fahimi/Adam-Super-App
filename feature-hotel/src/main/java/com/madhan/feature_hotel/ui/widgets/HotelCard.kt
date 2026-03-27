package com.madhan.feature_hotel.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.madhan.adamsuperapp.ui.theme.PrimaryColor
import com.madhan.feature_hotel.R
import com.madhan.feature_hotel.data.model.HotelLocation



@Composable
fun HotelCard(hotel: HotelLocation, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .clickable { onClick() }
            .padding(8.dp)
            .width(160.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = hotel.imageRes),
                contentDescription = "Hotel Image",
                modifier = Modifier.height(100.dp),
                contentScale = ContentScale.Crop
            )
            Text(hotel.name, fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.rate),
                    contentDescription = "Star",
                    tint = PrimaryColor
                )
                Text(hotel.rating.toString(), fontWeight = FontWeight.Bold)
            }
        }
    }
}

