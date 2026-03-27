package com.madhan.feature_tinder.dummydata

import com.madhan.feature_tinder.R

fun getProfiles(): List<Profile> {
    return listOf(
        Profile(
            photo = R.drawable.profile_1, // Replace with your image resources
            firstName = "Emily",
            lastName = "Smith",
            age = "29",
            city = "New York",
            description = "Adventurous and passionate about travel and photography.",
            milesAway = "15",
            photoViews = "1200",
            haveInteracted = false,
            haveRejected = false,
            haveLiked = false,
            haveSuperLiked = false
        ),
        Profile(
            photo = R.drawable.profile_2,
            firstName = "Sophia",
            lastName = "Johnson",
            age = "32",
            city = "Los Angeles",
            description = "Loves hiking, reading, and exploring new cuisines.",
            milesAway = "22",
            photoViews = "950",
            haveInteracted = false,
            haveRejected = false,
            haveLiked = false,
            haveSuperLiked = false
        ),
//        Profile(
//            photo = R.drawable.profile_3,
//            firstName = "Olivia",
//            lastName = "Williams",
//            age = "27",
//            city = "Chicago",
//            description = "Artist and musician, enjoys live music and art galleries.",
//            milesAway = "8",
//            photoViews = "1500",
//            haveInteracted = false,
//            haveRejected = false,
//            haveLiked = false,
//            haveSuperLiked = false
//        ),
//        Profile(
//            photo = R.drawable.profile_4,
//            firstName = "Ava",
//            lastName = "Brown",
//            age = "35",
//            city = "Houston",
//            description = "Fitness enthusiast and animal lover, always up for a good workout.",
//            milesAway = "28",
//            photoViews = "800",
//            haveInteracted = false,
//            haveRejected = false,
//            haveLiked = false,
//            haveSuperLiked = false
//        ),
//        Profile(
//            photo = R.drawable.profile_5,
//            firstName = "Isabella",
//            lastName = "Davis",
//            age = "31",
//            city = "Phoenix",
//            description = "Tech-savvy and loves coding, also enjoys outdoor activities.",
//            milesAway = "5",
//            photoViews = "1100",
//            haveInteracted = false,
//            haveRejected = false,
//            haveLiked = false,
//            haveSuperLiked = false
//        )
    )
}