package com.madhan.feature_tinder.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.madhan.adamsuperapp.ui.theme.PrimaryColor
import com.madhan.feature_tinder.R
import com.madhan.feature_tinder.TinderRoute
import com.madhan.feature_tinder.composable.CustomButton
import com.madhan.feature_tinder.composable.HeroImage
import com.madhan.feature_tinder.composable.TitleText
import com.madhan.feature_tinder.composable.TopBar

@Composable
fun PictureChoiceScreen01(
    modifier: Modifier = Modifier,
    navController: NavController? = null
) {
    Column(modifier = modifier) {
        TopBar(
            leftSlot = {
                Icon(
                    painter = painterResource(R.drawable.ic_return),
                    contentDescription = "",
                    modifier = Modifier
                        .scale(2f)
                        .clickable {
                            navController!!.navigate(TinderRoute.TinderScreen.route) {
                                popUpTo(TinderRoute.PictureChoice.route) {
                                    inclusive = true
                                }
                            }
                        }
                        .size(32.dp),
                    tint = PrimaryColor
                )
            }
        )
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .weight(10f)
            ) {
                HeroImage(drawable = R.drawable.img_tinder_screen_01)
                TitleText(
                    text = "Add your profile picture",
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "Add photo to personalize your space",
                    color = Color(0x00000000).copy(alpha = 0.3f),
                    textAlign = TextAlign.Center
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CustomButton(
                    text = "Add a picture",
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(64.dp),
                    icon = R.drawable.ic_camera
                )
                CustomButton(
                    text = "Take a picture",
                    onClick = {
                        navController!!.navigate(TinderRoute.TakePhoto.route)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 64.dp),
                    icon = R.drawable.ic_picture
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PictureChoiceScreenPreview() {
    PictureChoiceScreen01()
}