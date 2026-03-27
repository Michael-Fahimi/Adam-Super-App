package com.madhan.feature_uber.Screens.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.madhan.feature_uber.R

val orange = Color(0xFFFF7D1E)
val lightGray = Color(0xFFF5F5F5)
val blue = Color(0xFF2196F3)
val green = Color(0xFF4CAF50)

@Composable
fun PermissionScreen(    onBackClick: () -> Unit , onLetsGoClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Navigation (Simple Back Arrow)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = { onBackClick() }) {
                Icon(painterResource(id = android.R.drawable.ic_menu_revert), contentDescription = "Back")
            }
        }

        Spacer(modifier = Modifier.height(70.dp))

        // Illustration
        Image(
            painter = painterResource(id = R.drawable.img), // Replace with your image resource
            contentDescription = "Transport Illustration",
            modifier = Modifier.size(height = 230.dp, width = 307.dp)
        )

        Spacer(modifier = Modifier.height(90.dp))

        //     Title
        Row(
            modifier = Modifier
                .height(42.dp) // Set the height of the container
                .width(335.dp), // Set the width of the container
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Enable Geolocation",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .height(75.dp) // Set the height of the container
                .width(335.dp), // Set the width of the container
            horizontalArrangement = Arrangement.Center
        ) {
            // Description
            Text(
                text = "Please give access to your phone Location so that you have a more seamless experience ",
                fontSize = 16.sp,
            )
        }

        Spacer(modifier = Modifier.height(150.dp)) // Push button to bottom with 1f

        // Button
        Button(
            onClick = onLetsGoClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = orange),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Order",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PermissionScreenPreview() {
    MaterialTheme {
        PermissionScreen(
            onBackClick = TODO(),
            onLetsGoClick = TODO()
        )
    }
}