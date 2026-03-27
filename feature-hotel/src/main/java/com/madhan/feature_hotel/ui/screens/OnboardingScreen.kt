package com.madhan.feature_hotel.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.madhan.adamsuperapp.ui.theme.descriptionColor
import com.madhan.adamsuperapp.ui.theme.hotelTextColor
import com.madhan.feature_hotel.R
import com.madhan.core.ui.components.PrimaryButton
import com.madhan.feature_hotel.data.DummyData
import com.madhan.feature_hotel.ui.widgets.CustomIconButton
import com.madhan.feature_hotel.utils.routes.HOMESCREEN
import com.madhan.feature_hotel.utils.routes.HOTELSCREEN

@Composable
fun OnboardingScreen(navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        //Back arrow
//        CustomIconButton(
//            onClick = { navController.popBackStack()},
//            icon = painterResource(R.drawable.back_arrow),
//            contentDescription = "Back Arrow"
//        )
        //Vector image
        Image(
            painter = painterResource(R.drawable.woman),
            contentDescription = "onboarding image",
//            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.height(30.dp))
        //Hotel Text
        Text(
            modifier = Modifier.fillMaxWidth(),
            text="Hotel",
            style = TextStyle(
             fontSize = 32.sp,
                textAlign = TextAlign.Center,
             fontWeight = FontWeight.SemiBold,
                color = hotelTextColor
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        //Description
        Text(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
            text=DummyData.hotelDescription,
            softWrap = true,
            style = TextStyle(
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                color = descriptionColor,
            )
        )
        Spacer(modifier = Modifier.height(60.dp))
        //Button
       PrimaryButton(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                navController.navigate(HOMESCREEN){
                    popUpTo(HOTELSCREEN){inclusive=true}
                }
                      },
            text = "Let' go",
            width = 257.dp,
            height = 56.dp,
        )
    }
}