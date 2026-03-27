package com.madhan.feature_hotel.data

import com.google.android.gms.maps.model.LatLng
import com.madhan.feature_hotel.R
import com.madhan.feature_hotel.data.model.Hotel
import com.madhan.feature_hotel.data.model.HotelLocation

object DummyData {

    // Image Resource ID List
    val imageList: List<Int> = listOf(
        R.drawable.hotelimg1,
        R.drawable.hotelimg2,
        R.drawable.hotelimg3,
        R.drawable.hotelimg4,
        R.drawable.hotelimg5,
        R.drawable.hotelimg6,
        R.drawable.hotelimg7,
        R.drawable.hotelimg9,
    )
    //Room Images
    val hotelRoomList : List<Int> = listOf(
        R.drawable.hotelimgroom1,
        R.drawable.hotelimgroom2,
        R.drawable.hotelimgroom3,
        R.drawable.hotelimgroom4,
        R.drawable.hotelimgroom5,
        R.drawable.hotelimgroom6
    )

    //Hotel description
    val hotelDescription : String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis lobortis sit amet odio in egestas. Pellen tesque ultricies justo skajhdkjhfjsk kdahdksj  ikajsj ksj  lajkdl jlksdhihkeliusnksh ks k."

    //Hotel List
    val hotelList = listOf(
        Hotel(R.drawable.hotelimg1, "Resort Hotel", "New York", "4.5", "2 km to center", "$100"),
        Hotel(R.drawable.hotelimg2, "Luxury Hotel", "Los Angeles", "4.7", "1 km to beach", "$150"),
        Hotel(R.drawable.hotelimg3, "Business Hotel", "Chicago", "4.3", "3 km to airport", "$90"),
        Hotel(R.drawable.hotelimg4, "Boutique Hotel", "Miami", "4.8", "500m to city center", "$200"),
        Hotel(R.drawable.hotelimg5, "Budget Hotel", "San Francisco", "4.2", "5 km to downtown", "$60"),
        Hotel(R.drawable.hotelimg6, "Family Hotel", "Orlando", "4.6", "1.5 km to Disney", "$120")
    )
    val hotelLocationList = listOf(
        HotelLocation("Resort Hotel", LatLng(37.7749, -122.4194), 4.8, R.drawable.hotelimg1),
        HotelLocation("Windsor Hotel", LatLng(37.7849, -122.4094), 4.5, R.drawable.hotelimg2),
        HotelLocation("Grand Plaza", LatLng(37.7949, -122.3994), 4.7, R.drawable.hotelimg3),
        HotelLocation("Seaside Inn", LatLng(37.7649, -122.4294), 4.6, R.drawable.hotelimg4),
        HotelLocation("Skyline Suites", LatLng(37.7549, -122.4394), 4.9, R.drawable.hotelimg5)
    )

//Equipment List
val allEquipments = listOf("Restaurant", "Tennis", "Bar", "Wifi", "Parking", "Golf", "Pool", "Handy", "Spa")


}