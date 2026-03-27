package com.madhan.feature_tinder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.madhan.feature_tinder.dummydata.Profile
import com.madhan.feature_tinder.dummydata.getProfiles


class ProfileViewModel: ViewModel() {
    var _profiles: MutableLiveData<List<Profile>> = MutableLiveData(getProfiles())
    val profiles: LiveData<List<Profile>> = _profiles
    var _currentProfileIndex: MutableLiveData<Int> = MutableLiveData(0)
    val currentProfileIndex: LiveData<Int> = _currentProfileIndex


    fun incrementProfile() {
        _currentProfileIndex.value = (_currentProfileIndex.value!! + 1) % _profiles.value!!.size
    }

    fun updateProfile(
        interacted: Boolean = false,
        rejected: Boolean = false,
        liked: Boolean = false,
        superLiked: Boolean = false
    ) {
        _profiles.value?.let { currentProfiles ->
            _currentProfileIndex.value?.let { index ->
                if (index in currentProfiles.indices) {
                    val updatedProfiles = currentProfiles.toMutableList() // Create a mutable copy
                    val profile = updatedProfiles[index]

                    updatedProfiles[index] = profile.copy(
                        haveInteracted = interacted,
                        haveRejected = rejected,
                        haveLiked = liked,
                        haveSuperLiked = superLiked
                    )

                    _profiles.value = updatedProfiles // Assign the new list to MutableLiveData
                }
            }
        }
    }
}