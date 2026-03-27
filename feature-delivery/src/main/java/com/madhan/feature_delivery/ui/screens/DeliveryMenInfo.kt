package com.madhan.feature_delivery.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.madhan.feature_delivery.R
import com.madhan.feature_delivery.ui.components.HomeIcon

@Composable
fun DeliveryMenInfo(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HomeIcon(onClick = { navController.navigate("delivery_home") })
            }

            // Main Content
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                // Gradient Mask
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(353.dp)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.White.copy(alpha = 0f),
                                    Color.White.copy(alpha = 1f)
                                )
                            )
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.mask_group),
                        contentDescription = "Delivery Image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(230.dp))

                    // Content Card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White, RoundedCornerShape(8.dp))
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "Marietta, 1 Park Plaza",
                                Modifier.navigationBarsPadding(),
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(text = "CHOOSE DATES")
                                Text(text = "KG")
                                Text(text = "DIMENSIONS")
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(text = "20 Mar - 10h")
                                Text(text = "5")
                                Text(text = "50/50/50")
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            OutlinedTextField(
                                value = "",
                                onValueChange = {},
                                placeholder = { Text("Search location/name") },
                                modifier = Modifier.fillMaxWidth(),
                                trailingIcon = {
                                    IconButton(onClick = { /* Handle search */ }) {
                                        Icon(
                                            imageVector = Icons.Default.Search,
                                            contentDescription = "Search"
                                        )
                                    }
                                }
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            // Favorites and Orders Buttons
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Button(
                                    onClick = { /* Handle Favorites */ },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF8000)),
                                    modifier = Modifier.width(136.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.favorites),
                                        modifier = Modifier.size(20.dp),
                                        contentDescription = "Favorites",
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(text = "Favorites")
                                }

                                Button(
                                    onClick = { navController.navigate("orders") },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF8000)),
                                    modifier = Modifier.width(136.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.orders),
                                        modifier = Modifier.size(20.dp),
                                        contentDescription = "Orders",
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(text = "Orders")
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Delivery men",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        IconButton(onClick = { navController.navigate("filter") }) {
                            Icon(
                                painter = painterResource(id = R.drawable.filter),
                                contentDescription = "Filters",
                            )
                        }
                    }

                    // Delivery men list
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(
                            listOf(
                                Triple("Jenny Jones", 4.8f, R.drawable.delivery_guy),
                                Triple("Sacha Down", 4.6f, R.drawable.delivery_guy2),
                                Triple("Johnathan James", 4.3f, R.drawable.delivery_guy3),
                                Triple("William", 4.7f, R.drawable.delivery_guy4)
                            )
                        ) { (name, rating, imageRes) ->
                            DriverCard1(name, rating, 4.5f, imageRes, navController) {
                                navController.navigate("map_search")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DriverCard1(
    name: String,
    rating: Float,
    distance: Float,
    imageRes: Int,
    navController: NavController,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Image fills max width of the card
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Delivery Person's Name
            Text(text = name, fontWeight = FontWeight.Bold, fontSize = 18.sp)

            Spacer(modifier = Modifier.height(4.dp))

            // Rating Row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "Rating",
                    tint = Color.Yellow,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "$rating", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Distance Info
            Text(text = "$distance Mile Nearby", fontSize = 14.sp, color = Color.Gray)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DeliveryMenPreview() {
    DeliveryMenInfo(rememberNavController())
}
