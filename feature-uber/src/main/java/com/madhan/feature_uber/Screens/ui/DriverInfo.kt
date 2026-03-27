package com.madhan.feature_uber.Screens.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AirplanemodeActive
import androidx.compose.material.icons.outlined.BrokenImage
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.Motorcycle
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Driver(
    val name: String,
    val rating: Double,
    val car: String,
    val carColor: String,
    val estimatedTime: Int,
    val estimatedPriceRange: String
)

@Composable
fun RideOptionsScreen(
    onBackClick: () -> Unit,
    onDriverSelected: (Driver) -> Unit
) {
    val orange = Color(0xFFFF7D1E)
    val lightGray = Color(0xFFF5F5F5)
    val mediumGray = Color(0xFFAAAAAA)

    val drivers = remember {
        listOf(
            Driver("Sina F.", 4.8, "Batmobile", "Red", 3, "$8-10"),
            Driver("Michael F.", 4.2, "ferrari LA", "Red", 4, "$9-11"),
            Driver("Adam M.", 4.6, "Airbus A380", "Red", 5, "$9-11"),
            Driver("Simon A.", 4.8, "Ford Mustang", "Red", 3, "$12-14"),
            Driver("Riya D.", 3.2, "Hyundai accent", "Red", 9, "$16-18"),
            Driver("Madhan G.", 4.9, "Toyota Corolla", "Blue", 2, "$7-9"),
            Driver("Usman A.", 4.0, "Cadillac Escalade", "Black", 7, "$10-12"),
            Driver("Gus D.", 4.7, "BMW M3", "Silver", 4, "$11-13")
        )
    }

    var selectedVehicleType by remember { mutableStateOf(0) }
    val vehicleTypes = listOf("Car", "Van", "Motorcycle")

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(lightGray)
            ) {
                // Map placeholder
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = orange
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Vehicle type selector
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                vehicleTypes.forEachIndexed { index, type ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { selectedVehicleType = index }
                    ) {
                        val icon = when (index) {
                            0 -> Icons.Default.DirectionsCar // Using a generic transportation icon for Car
                            1 -> Icons.Outlined.Motorcycle // Using a generic transportation icon for Van
                            2 -> Icons.Outlined.AirplanemodeActive // Keeping this as a placeholder, consider a different icon
                            else -> Icons.Outlined.BrokenImage // Fallback icon
                        }
                        Icon(
                            imageVector = icon,
                            contentDescription = type,
                            tint = if (selectedVehicleType == index) orange else mediumGray,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        if (selectedVehicleType == index) {
                            HorizontalDivider(
                                modifier = Modifier.width(24.dp),
                                thickness = 2.dp,
                                color = orange
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Seats selector
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clickable { /* handle click */ },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Seats",
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "2 seats",
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = "More",
                    tint = mediumGray
                )
            }

            HorizontalDivider(thickness = 5.dp, color = lightGray)

            // Driver list
            LazyColumn {
                items(drivers) { driver ->
                    DriverItem(
                        driver = driver,
                        onClick = { onDriverSelected(driver) }
                    )
                    HorizontalDivider(thickness = 1.dp, color = lightGray.copy(alpha = 0.5f))
                }
            }
        }
    }
}

@Composable
fun DriverItem(
    driver: Driver,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Driver photo
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Driver info
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = driver.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color(0xFFFFB800),
                    modifier = Modifier.size(16.dp)
                )

                Text(
                    text = driver.rating.toString(),
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 2.dp)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Text(
                    text = "${driver.car}",
                    color = Color.Gray,
                    fontSize = 14.sp
                )

                Text(
                    text = " ${driver.carColor}",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }

        // Time and price
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "Estimate",
                color = Color.Gray,
                fontSize = 12.sp
            )

            Text(
                text = driver.estimatedPriceRange,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 4.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(14.dp)
                )

                Text(
                    text = "${driver.estimatedTime} min",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 2.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RideOptionsScreenPreview() {
    MaterialTheme {
        RideOptionsScreen(
            onBackClick = {},
            onDriverSelected = {}
        )
    }
}