package com.madhan.feature_uber.Screens.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Loupe
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.madhan.feature_uber.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmDestinationScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { /* Empty title to match the image */ },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back click */ }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },



        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth() // Make sure the column fills the width
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)) // Clip with rounded corners at the top
                    .border(3.dp, Color.LightGray, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)) // Border only at the top
                    .background(Color.White) // Add background to prevent transparency issues
                    .padding(16.dp) // Add padding to keep content spaced from the border
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Set Pick Up Location", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                    IconButton(onClick = { /* Handle calendar click */ }) {
                        Icon(Icons.Filled.CalendarMonth, contentDescription = "Calendar")
                    }
                }



                Spacer(modifier = Modifier.height(8.dp))


                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.LocationOn, contentDescription = "Location")
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text("Johannesburg", fontWeight = FontWeight.SemiBold , fontSize = 20.sp)
                        Text("28 Orchard Road", fontSize = 14.sp)
                    }
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "Map",
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(Color.Blue)
            )
            FloatingActionButton(
                onClick = { /* Handle target click */ },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(Icons.Filled.Loupe, contentDescription = "Add")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConfirmDestinationScreenPreview() {
    val context = LocalContext.current
    CompositionLocalProvider(LocalContext provides context) {
        ConfirmDestinationScreen()
    }
}
