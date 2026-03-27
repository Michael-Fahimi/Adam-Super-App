package com.madhan.feature_hotel.data.vm


import androidx.lifecycle.ViewModel
import com.madhan.feature_hotel.data.model.Hotel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavoriteViewModel : ViewModel() {
    private val _favoriteHotels = MutableStateFlow<List<Hotel>>(emptyList())
    val favoriteHotels: StateFlow<List<Hotel>> = _favoriteHotels.asStateFlow()

    fun toggleFavorite(hotel: Hotel) {
        val currentList = _favoriteHotels.value.toMutableList()
        if (currentList.contains(hotel)) {
            currentList.remove(hotel)
        } else {
            currentList.add(hotel)
        }
        _favoriteHotels.value = currentList
    }

    fun isFavorite(hotel: Hotel): Boolean {
        return _favoriteHotels.value.contains(hotel)
    }
}
