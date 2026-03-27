package com.madhan.feature_hotel.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.madhan.feature_hotel.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.madhan.adamsuperapp.ui.theme.PrimaryColor
import com.madhan.core.ui.components.PrimaryButton
import com.madhan.feature_hotel.ui.widgets.CustomIconButton
import com.madhan.feature_hotel.ui.widgets.FilterOptions
import com.madhan.feature_hotel.utils.routes.FILTERSCREEN
import com.madhan.feature_hotel.utils.routes.HOMESCREEN
import com.madhan.feature_hotel.utils.routes.HOTELDETAILSCREEN
import com.madhan.feature_hotel.utils.routes.ORDERSCREEN

@Composable
fun FilterScreen(navController: NavController) {
    val priceRange = remember { mutableFloatStateOf(300f) }
    val selectedStars = remember { mutableIntStateOf(3) }
    val selectedEquipments = remember { mutableStateListOf("Tennis") }
    val allEquipments = listOf("Tennis", "Soccer", "Basketball", "Badminton", "Golf", "Baseball")

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomIconButton(
                    icon = painterResource(id = R.drawable.back_arrow),
                    onClick = {
                        navController.navigate(HOMESCREEN){
                            popUpTo(FILTERSCREEN){inclusive=true}
                        }
                              },
                    contentDescription = "back arrow",
                    iconSize = 20.dp
                )
                Text(
                    text = "Filters",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                TextButton(onClick = {
                    priceRange.floatValue = 300f
                    selectedStars.intValue = 3
                    selectedEquipments.clear()
                }) {
                    Text("Clear", color = PrimaryColor)
                }
            }

            Spacer(Modifier.height(20.dp))

            // Use reusable composable for Price, Rate & Equipments
            FilterOptions(priceRange, selectedStars, selectedEquipments, allEquipments)

            Spacer(Modifier.height(30.dp))

            // Apply Button
            PrimaryButton(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                    navController.navigate(HOTELDETAILSCREEN){
                        popUpTo(FILTERSCREEN){inclusive=true}
                    }
                },
                text = "Apply"
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FilterScreenPreview() {
    val navController = rememberNavController()
    FilterScreen(navController = navController)
}
