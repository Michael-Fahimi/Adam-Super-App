package com.madhan.feature_bank.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.madhan.adamsuperapp.ui.theme.PrimaryColor
import com.madhan.feature_bank.R
import com.madhan.feature_bank.data.CountryData

@Composable
fun BankTab(item: CountryData, navController: NavController){

    Card (
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .shadow(4.dp, shape = RoundedCornerShape(4.dp))
            .clickable {
                navController.navigate(item.route)
            }
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(343.dp)
                .height(60.dp)
                .background(color = Color.White)
                .padding(8.dp)
        ) {

            Spacer(modifier = Modifier.size(8.dp))

            Image(
                painter = painterResource(id = item.drawableId),
                contentDescription = "icon name",
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.size(16.dp))

            Text(
                text = item.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(id = R.drawable.next_arrow),
                contentDescription = "back button",
                tint = PrimaryColor,
                modifier = Modifier.width(20.dp).height(20.dp)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun BankTabPreview(){
    BankTab(CountryData(0, "sample", ""), rememberNavController())
}