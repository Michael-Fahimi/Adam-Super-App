package com.madhan.feature_tinder.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.madhan.feature_tinder.R
import com.madhan.feature_tinder.dummydata.Profile

@Composable
fun SwipeableCard(
    modifier: Modifier = Modifier,
    profile: Profile
) {
    Column(
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box {
            Image(
                painter =  painterResource(profile.photo),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f/1f),
                contentScale = ContentScale.FillWidth
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row {
                    Text(
                        text = "${profile.firstName}, ${profile.age}",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                Text(
                    text = profile.city,
                    color = Color(0x00000000).copy(alpha = 0.3f)
                )
                Text(
                    text = "${profile.milesAway} miles away",
                    color = Color(0x00000000).copy(alpha = 0.3f)
                )
            }
            Row {
                Text(text = profile.photoViews)
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    painter = painterResource(R.drawable.ic_picture),
                    contentDescription = "",
                    modifier = Modifier
                        .size(18.dp)
                )
            }
        }
        Text(
            text = profile.description,
            color = Color(0x00000000).copy(alpha = 0.3f)
        )
    }
}