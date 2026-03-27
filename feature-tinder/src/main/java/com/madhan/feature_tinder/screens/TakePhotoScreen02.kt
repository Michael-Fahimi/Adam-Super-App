package com.madhan.feature_tinder.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.madhan.adamsuperapp.ui.theme.PrimaryColor
import com.madhan.core.ui.screen.TakePhotoScreen
import com.madhan.feature_tinder.R
import com.madhan.feature_tinder.TinderRoute
import com.madhan.feature_tinder.composable.CustomButton
import com.madhan.feature_tinder.composable.TitleText
import com.madhan.feature_tinder.composable.TopBar

@Composable
fun TakePhotoScreen02(
    modifier: Modifier = Modifier,
    navController: NavController? = null
){
    TakePhotoScreen(
        onBackButtonClick = {
            navController!!.navigate(TinderRoute.PictureChoice.route) {
                    popUpTo(TinderRoute.TakePhoto.route) {
                        inclusive = true
                    }
            }
        },
        onCameraLaunchSuccess = {
            navController!!.navigate(TinderRoute.AddProfile.route)
        }
    )
//    val config = LocalConfiguration.current
//    val screenHeight = config.screenHeightDp.dp
//    val imageHeight = screenHeight * 0.75f
//    Column(modifier = modifier) {
//        TopBar(
//            leftSlot = {
//                Icon(
//                    painter = painterResource(R.drawable.ic_return),
//                    contentDescription = "",
//                    modifier = Modifier
//                        .clickable {
//                            navController!!.navigate(TinderRoute.PictureChoice.route) {
//                                popUpTo(TinderRoute.TakePhoto.route) {
//                                    inclusive = true
//                                }
//                            }
//                        }
//                        .scale(2f)
//                        .size(32.dp),
//                    tint = PrimaryColor
//                )
//            },
//            middleSlot = {
//                TitleText(
//                    text = "Take a photo",
//                    style = MaterialTheme.typography.headlineSmall
//                )
//            },
//            rightSlot = {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.spacedBy(16.dp)
//                ) {
//                    Icon(
//                        painter = painterResource(R.drawable.ic_picture),
//                        contentDescription = "",
//                        modifier = Modifier
//                            .size(16.dp),
//                        tint = PrimaryColor
//                    )
//
//                    Icon(
//                        painter = painterResource(R.drawable.ic_question),
//                        contentDescription = "",
//                        modifier = Modifier
//                            .size(32.dp),
//                        tint = PrimaryColor
//                    )
//                }
//            }
//        )
//        Column(
//            modifier = modifier
//                .fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            Column(
//                modifier = Modifier
//                    .height(imageHeight)
//                    .fillMaxWidth()
//                    .background(Color.Red)
//            ) {
//
//            }
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(32.dp)
//            ) {
//                CustomButton(
//                    icon = R.drawable.ic_camera,
//                    onClick = {
//                        navController!!.navigate(TinderRoute.AddProfile.route)
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .heightIn(min = 64.dp)
//                )
//            }
//        }
//    }
}

@Preview(showBackground = true)
@Composable
fun TakePhotoScreen02Preview() {
//    TakePhotoScreen02()
}