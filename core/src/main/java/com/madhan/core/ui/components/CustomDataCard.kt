package com.madhan.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.madhan.adamsuperapp.ui.theme.PrimaryColor
import com.madhan.adamsuperapp.ui.theme.cardColor
import com.madhan.adamsuperapp.ui.theme.descriptionColor
import com.madhan.adamsuperapp.ui.theme.hotelTextColor
import com.madhan.core.R

@Composable
fun CustomDataCard(
    modifier: Modifier = Modifier,
    backgroundImage: Painter,
    hotelType: String,
    hotelLocation: String,
    hotelRating: String,
    hotelDistance: String,
    hotelPrice: String,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,

    ) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(240.dp)
            .padding(16.dp),
        colors = CardDefaults.cardColors(
//            containerColor = cardColor,
            containerColor = Color(0XFFE8E9EC),
            contentColor = Color(0xFF171D33)
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Background image
            Image(
                painter = backgroundImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(136.dp)
            )

            // Like Icon
            IconButton(
                modifier = Modifier.align(Alignment.TopEnd),
                onClick = {
                    onFavoriteClick()
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.White
                )
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(if (isFavorite) R.drawable.heartfill else R.drawable.heart),
                    contentDescription = "like icon",
                    tint = Color.White
                )
            }

            // Texts placed inside the Box, aligned to the bottom
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
            ) {
                CustomTitleText(
                    modifier = Modifier.padding(top = 10.dp),
                    text = hotelType,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                )
                Spacer(Modifier.height(4.dp))
                CustomTitleText(
                    modifier = Modifier.padding(bottom = 5.dp),
                    text = hotelLocation,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Start
                )

                // Divider
                HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Rating
                    IconTextRow(
                        icon = painterResource(R.drawable.rate),
                        text = hotelRating,
                        iconTint = PrimaryColor
                    )
                    // Distance
                    IconTextRow(
                        icon = painterResource(R.drawable.nav),
                        text = hotelDistance,
                        iconTint = descriptionColor
                    )
                    // Price
                    IconTextRow(
                        icon = painterResource(R.drawable.moon),
                        text = hotelPrice,
                        iconTint = descriptionColor
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomDataCardPreview() {
    CustomDataCard(
        modifier = Modifier.clickable {},
        backgroundImage = painterResource(id = R.drawable.hotelimg2),
        hotelType = "Resort Hotel",
        hotelLocation = "Smyrna",
        hotelRating = "4.8",
        hotelDistance = "3.56 km ",
        hotelPrice = "$ 150",
        isFavorite = false,
        onFavoriteClick = {}
    )
}