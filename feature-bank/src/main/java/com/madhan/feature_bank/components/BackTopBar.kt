package com.madhan.feature_bank.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
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
import com.madhan.adamsuperapp.ui.theme.PrimaryColor
import com.madhan.feature_bank.R

@Composable
fun BackTopBar(title: String, navController: NavController, onBackClick: () -> Unit = {}) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.back_button),
            contentDescription = "back button",
            tint = PrimaryColor,
            modifier = Modifier
                .width(28.dp)
                .height(17.dp)
                .clickable {
                    onBackClick()
                }
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = title,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
        )

        Spacer(modifier = Modifier.weight(1f))

    }
}

@Preview(showBackground = true)
@Composable
fun BackTopBarPreview() {
    BackTopBar("", rememberNavController())
}