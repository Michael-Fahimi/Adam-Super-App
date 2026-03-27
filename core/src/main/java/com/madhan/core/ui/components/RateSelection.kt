package com.madhan.core.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.madhan.adamsuperapp.ui.theme.PrimaryColor
import com.madhan.core.R

@Composable
fun RateSelection(selectedStars: MutableState<Int>) {
    Column {
        Text("Rate", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(5) { index ->
                Icon(
                    painter = painterResource(id = if (index < selectedStars.value) R.drawable.rate else R.drawable.unrate),
                    contentDescription = "Star Rating",
                    tint = if (index < selectedStars.value) PrimaryColor else Color.Gray,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { selectedStars.value = index + 1 }
                )
            }
        }
    }
}
