package com.madhan.feature_hotel.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.madhan.adamsuperapp.ui.theme.PrimaryColor
import com.madhan.feature_hotel.data.vm.FavoriteViewModel
import com.madhan.feature_hotel.ui.widgets.CustomHotelCard
import com.madhan.feature_hotel.utils.routes.FAVORITESCREEN
import com.madhan.feature_hotel.utils.routes.HOTELDETAILSCREEN
import com.madhan.feature_hotel.utils.routes.PLACESCREEN


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(navController: NavController, viewModel: FavoriteViewModel = viewModel()) {
    val favoriteHotels by viewModel.favoriteHotels.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Favorites", color = PrimaryColor) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) { // Pop back to previous screen
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = PrimaryColor
                        )
                    }
                }
            )
        }    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (favoriteHotels.isEmpty()) {
                Text(
                    text = "No favorite hotels yet",
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 18.sp,
                    color = Color.Gray
                )
            } else {
                LazyColumn {
                    items(favoriteHotels) { hotel ->
                        CustomHotelCard(
                            modifier = Modifier.clickable {
                                navController.navigate(HOTELDETAILSCREEN){
                                    popUpTo(FAVORITESCREEN){inclusive=true}
                                }
                                                          },
                            backgroundImage = painterResource(id = hotel.imageResId),
                            hotelType = hotel.hotelType,
                            hotelLocation = hotel.hotelLocation,
                            hotelRating = hotel.hotelRating,
                            hotelDistance = hotel.hotelDistance,
                            hotelPrice = hotel.hotelPrice,
                            isFavorite = true,
                            onFavoriteClick = { viewModel.toggleFavorite(hotel) }
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun FavoriteScreenPreview(){
    //Viewmodel instance
    val favoriteViewModel:FavoriteViewModel = viewModel()
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        FavoriteScreen(
            navController = rememberNavController(),
            viewModel = favoriteViewModel
        )
    }
}