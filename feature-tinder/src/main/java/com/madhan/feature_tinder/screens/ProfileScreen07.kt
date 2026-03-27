package com.madhan.feature_tinder.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.madhan.feature_tinder.R
import com.madhan.feature_tinder.TinderRoute
import com.madhan.feature_tinder.composable.BottomButtonRow
import com.madhan.feature_tinder.composable.SwipeableCard
import com.madhan.feature_tinder.composable.TopBar
import com.madhan.feature_tinder.dummydata.getProfiles
import com.madhan.feature_tinder.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen07(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
    viewModel: ProfileViewModel
) {
    val profiles = viewModel.profiles.observeAsState(listOf()).value
    val currentProfileIndex = viewModel.currentProfileIndex.observeAsState(
        viewModel.currentProfileIndex.value ?: 0
    ).value
    val profile = profiles[currentProfileIndex]
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxSize()
    ) {
        Column {
            TopBar(
                leftSlot = {
                    Icon(
                        painter = painterResource(R.drawable.ic_return),
                        contentDescription = "",
                        modifier = Modifier
                            .scale(2f)
                            .clickable {
                                navController!!.navigate(TinderRoute.EnableLocation.route)
                            }
                            .size(32.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            )
            SwipeableCard(
                profile = profile
            )
        }
        BottomButtonRow(
            showBack = false,
            onDenyClicked = {
                profiles[currentProfileIndex].apply {
                    viewModel.updateProfile(
                        interacted = true,
                        rejected = true,
                        liked = false,
                        superLiked = false
                    )
                }
                viewModel.incrementProfile()
            },
            onLikedClicked = {
                profiles[currentProfileIndex].apply {
                    viewModel.updateProfile(
                        interacted = true,
                        rejected = false,
                        liked = true,
                        superLiked = false
                    )
                }
                viewModel.incrementProfile()
            },
            onSuperLikedClicked = {
                profiles[currentProfileIndex].apply {
                    viewModel.updateProfile(
                        interacted = true,
                        rejected = false,
                        liked = false,
                        superLiked = true
                    )
                }
                viewModel.incrementProfile()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreen07Preview() {
//    ProfileScreen07()
}


//                    .clickable {
//                        navController!!.navigate(TinderRoute.TinderDisplayScreen.route)
//                    }