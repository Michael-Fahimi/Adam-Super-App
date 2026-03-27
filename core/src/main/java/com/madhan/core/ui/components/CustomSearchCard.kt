package com.madhan.core.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.madhan.core.R

@Composable
fun CustomSearchCard(modifier: Modifier,
                     onClick: () -> Unit,
                     onChooseDatesClick: () -> Unit,
                     startDate: String,
                     endDate: String
) {
    Box(modifier = Modifier.padding(16.dp)) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(226.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = modifier.padding(16.dp)) {
                // Location Row
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Johannesburg",
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                    )
                    //Icon Text
                    IconTextRow(
                        modifier = modifier.clickable { onClick() },
                        icon = painterResource(id = R.drawable.map),
                        text = "Places",
                        textSize = 20.sp,
                        textColor = Color(0xFFA6AAB4),
                        iconTint = Color(0xFFFF7A1A),
                        iconSize = 24.dp
                    )
                }
                // Date and Room Selection
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = modifier.weight(1f)
                        .clickable{onChooseDatesClick()}) {
                        Text(
                            text = "CHOOSE DATES",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.Gray
                        )
                        Text(text = if (startDate.isNotEmpty() && endDate.isNotEmpty()) "$startDate - $endDate" else "Choose Dates",
                            style = MaterialTheme.typography.bodyMedium)
                    }
                    Spacer(modifier = modifier.width(8.dp))
                    Column(
                        modifier = modifier.weight(1f).clickable{
                            onChooseDatesClick()
                        }
                    ) {
                        Text(
                            text = "NUMBERS OF --",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.Gray
                        )
                        Text(
                            text = "Number of --",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                // Search Bar
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Search location / name / country") },
                    leadingIcon = {
                        Icon(
                            modifier = modifier.clickable { },
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = "Search"
                        )
                    },
                    modifier = modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.LightGray,
                        focusedBorderColor = Color(0xFFFF7A1A)
                    )
                )
                // Search Button
                Button(
                    onClick = { /* Handle search action */ },
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7A1A))
                ) {
                    Text(text = "Search hotels", color = Color.White)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CustomSearchCardPreview() {
    CustomSearchCard(
        modifier = Modifier,
        onClick = {},
        onChooseDatesClick = {},
        startDate = "10 Apr",
        endDate = "15 Apr"
    )
}