package com.madhan.feature_tinder.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.madhan.adamsuperapp.ui.theme.PrimaryColor
import com.madhan.core.ui.components.CustomButton
import com.madhan.feature_tinder.R
import com.madhan.feature_tinder.TinderRoute
import com.madhan.feature_tinder.composable.HeroImage
import com.madhan.feature_tinder.composable.TitleText
import com.madhan.feature_tinder.composable.TopBar

@Composable
fun TinderScreen00(
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
                        .clickable {
                            navController!!.navigate("home") {
                                popUpTo(TinderRoute.TinderScreen.route) {
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
            modifier = modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                HeroImage(
                    drawable = R.drawable.img_tinder_screen_00,
                )
                TitleText(text = "Meet")
                Text(
                    text = "lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis lobortis sit amet odio in egestas. Pellen tesque ultricies justo.",
                    color = Color(0x00000000).copy(alpha = 0.3f),
                    textAlign = TextAlign.Center
                )
            }
            CustomButton(
                buttonText = "Let's go",
                onClick = {
                    navController!!.navigate(TinderRoute.PictureChoice.route)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TinderScreenPreview() {
//    TinderScreen()
}