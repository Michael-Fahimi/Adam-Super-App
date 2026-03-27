package com.madhan.adamsuperapp.auth.google

import android.content.Context
import com.google.firebase.auth.FirebaseUser
import com.madhan.adamsuperapp.utils.UiStatus
import kotlinx.coroutines.flow.Flow

interface GoogleLoginRepository {
    suspend fun handleLogin(context: Context) : Flow<UiStatus<FirebaseUser>>

    suspend fun handleLogout(): Flow<UiStatus<Unit>>
}