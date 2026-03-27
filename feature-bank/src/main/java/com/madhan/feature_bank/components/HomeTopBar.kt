package com.madhan.feature_bank.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.madhan.feature_bank.R

@Composable
fun HomeTopBar(title: String, navController: NavController){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp)
    ) {


        Image(
            painter = painterResource(id = R.drawable.home_icon),
            contentDescription = "home icon",
            modifier = Modifier.size(24.dp)
                .clickable {
                    navController.navigateUp()
                }
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = title,
            fontWeight = FontWeight.Medium,
            fontSize = 22.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.card_icon),
            contentDescription = "card icon",
            modifier = Modifier.size(24.dp)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun HomeTapBarPreview(){
    HomeTopBar("", rememberNavController())
}