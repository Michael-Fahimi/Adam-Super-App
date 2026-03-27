package com.madhan.feature_tinder.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
fun EnableLocationScreen04(
    modifier: Modifier = Modifier,
    navController: NavController? = null
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            TopBar(
                leftSlot = {
                    Icon(
                        painter = painterResource(R.drawable.ic_return),
                        contentDescription = "",
                        modifier = Modifier
                            .clickable {
                                navController!!.navigate(TinderRoute.AddProfile.route)  {
                                    popUpTo(TinderRoute.EnableLocation.route) {
                                        inclusive = true
                                    }
                                }
                            }
                            .scale(2f)
                            .size(32.dp),
                        tint = PrimaryColor
                    )
                }
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(32.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HeroImage(drawable = R.drawable.img_tinder_screen_04)
                    TitleText(
                        text = "Enable geolocation",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = "To propose profiles near you, you must activate localization",
                        textAlign = TextAlign.Center,
                        color = Color(0x00000000).copy(alpha = 0.3f)
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            CustomButton(
                text = "Activate",
                onClick = {
                    navController!!.navigate(TinderRoute.TutorialScreen.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(64.dp)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun EnableLocationScreen04Preview() {
//    EnableLocationScreen04()
}