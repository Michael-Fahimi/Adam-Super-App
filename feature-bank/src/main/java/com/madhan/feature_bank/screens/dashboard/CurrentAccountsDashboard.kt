package com.madhan.feature_bank.screens.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CurrentAccountDashboard(){
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(end = 16.dp)
    ){
        Text(
            text = "Your total balance",
            color = Color.Gray,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "$ 936.21",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )

        DashboardTabRow()
    }
}

@Preview(showBackground = true)
@Composable
fun CurrentAccountDashboardPreview(){
    CurrentAccountDashboard()
}