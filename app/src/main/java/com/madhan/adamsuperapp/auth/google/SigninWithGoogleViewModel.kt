package com.madhan.adamsuperapp.auth.google

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.madhan.adamsuperapp.utils.UiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SigninWithGoogleViewModel @Inject constructor(
    private val googleLoginRepository: GoogleLoginRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _loginState = MutableStateFlow<UiStatus<FirebaseUser>>(UiStatus.LOADING)
    val loginState: StateFlow<UiStatus<FirebaseUser>> = _loginState.asStateFlow()

    private val _logoutState = MutableStateFlow<UiStatus<Unit>>(UiStatus.LOADING)
    val logoutState: StateFlow<UiStatus<Unit>> = _logoutState.asStateFlow()


    fun authenticate(context: Context) {
        viewModelScope.launch(dispatcher) {
            googleLoginRepository.handleLogin(context).collect {
                _loginState.value = it
            }
        }
    }

    fun logOut() {
        viewModelScope.launch(dispatcher) {
            googleLoginRepository.handleLogout().collect {
                _logoutState.value = it
            }
        }
    }
}