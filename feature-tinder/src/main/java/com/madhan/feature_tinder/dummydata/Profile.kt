package com.madhan.feature_tinder.dummydata

import androidx.annotation.DrawableRes
import java.time.LocalDate
import java.util.Date

data class Profile(
    @DrawableRes val photo: Int,
    val firstName: String,
    val lastName: String,
    val age: String,
    val city: String,
    val description: String,
    val milesAway: String,
    val photoViews: String,
    var haveInteracted: Boolean = false,
    var haveRejected: Boolean = false,
    var haveLiked: Boolean = false,
    var haveSuperLiked: Boolean = false
)

data class Photo(
    @DrawableRes val drawable: Int
)