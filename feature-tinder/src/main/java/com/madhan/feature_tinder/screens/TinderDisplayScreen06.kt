package com.madhan.feature_tinder.screens

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.madhan.adamsuperapp.ui.theme.PrimaryColor
import com.madhan.feature_tinder.R
import com.madhan.feature_tinder.TinderRoute
import com.madhan.feature_tinder.composable.BottomButtonRow
import com.madhan.feature_tinder.composable.CardBgColor
import com.madhan.feature_tinder.composable.ImageCard
import com.madhan.feature_tinder.composable.TopBar
import com.madhan.feature_tinder.viewmodel.ProfileViewModel

// DISPLAY SCREEN IS A MAJOR STATE HOLDER

@Composable
fun TinderDisplayScreen06(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
    viewModel: ProfileViewModel
) {
    val profiles = viewModel.profiles.observeAsState(listOf()).value
    val currentProfileIndex = viewModel.currentProfileIndex.observeAsState(
        viewModel.currentProfileIndex.value ?: 0
    ).value
    val profile = profiles[currentProfileIndex]
    var cardBgColor by remember { mutableStateOf(CardBgColor.NONE)}
    LaunchedEffect(profile) {
        if (profile.haveRejected && cardBgColor != CardBgColor.REJECTED) {
            cardBgColor = CardBgColor.REJECTED
        } else if (profile.haveLiked && cardBgColor != CardBgColor.LIKED) {
            cardBgColor = CardBgColor.LIKED
        } else if (profile.haveSuperLiked && cardBgColor != CardBgColor.SUPERLIKED) {
            cardBgColor = CardBgColor.SUPERLIKED
        } else if (!profile.haveRejected && !profile.haveLiked && !profile.haveSuperLiked && cardBgColor != CardBgColor.NONE) {
            cardBgColor = CardBgColor.NONE
        }
    }
    val nextProfile = profiles[(currentProfileIndex + 1) % profiles.size]
    var nextColor = CardBgColor.NONE
    if(nextProfile.haveLiked) nextColor = CardBgColor.LIKED
    if(nextProfile.haveRejected) nextColor = CardBgColor.REJECTED
    if(nextProfile.haveSuperLiked) nextColor = CardBgColor.SUPERLIKED
    fun updateColor(newBgColor: CardBgColor) {
        cardBgColor = newBgColor
    }
    Log.d("Swipe", profile.toString())
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            TopBar(
                leftSlot = {
                    Icon(
                        painter = painterResource(R.drawable.ic_home),
                        contentDescription = "",
                        modifier = Modifier
                            .clickable {
                                navController!!.navigate("home") {
                                    popUpTo(TinderRoute.TinderScreen.route) {
                                        inclusive = true
                                    }
                                }
                            }
                            .size(32.dp),
                        tint = PrimaryColor
                    )
                },
                middleSlot = {
                    Icon(
                        painter = painterResource(R.drawable.ic_chat),
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp),
                        tint = PrimaryColor
                    )
                },
                rightSlot = {
                    Icon(
                        painter = painterResource(R.drawable.ic_account),
                        contentDescription = "",
                        modifier = Modifier
                            .size(32.dp),
                        tint = PrimaryColor
                    )
                }
            )
            ImageCard(
                profile = profile,
                currentColor = cardBgColor,
                nextColor = nextColor,
                updateColor = { color -> updateColor(color) },
                onDismissedLeft = {
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
                onDismissedRight = {
                    profiles[currentProfileIndex].apply {
                        viewModel.updateProfile(
                            interacted = true,
                            rejected = false,
                            liked = true,
                            superLiked = false
                        )
                    }
                    viewModel.incrementProfile()
                }
            )
        }
        BottomButtonRow(
            onBack = {
                navController!!.popBackStack()
            },
            onDenyClicked = {
                profiles[currentProfileIndex].apply {
                    viewModel.updateProfile(
                        interacted = true,
                        rejected = true,
                        liked = false,
                        superLiked = false
                    )
                }
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
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TinderDisplayScreen06Preview() {
//    TinderDisplayScreen06()
}

