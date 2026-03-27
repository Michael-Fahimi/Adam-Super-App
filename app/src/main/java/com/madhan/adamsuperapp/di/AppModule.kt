package com.madhan.adamsuperapp.di

import android.content.Context
import android.credentials.CredentialManager
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.madhan.adamsuperapp.auth.google.GoogleLoginRepository
import com.madhan.adamsuperapp.auth.google.GoogleLoginRepositoryImpl
import com.madhan.adamsuperapp.utils.OAUTH_ID
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    //Dispatchers.io
    @Provides
    @Singleton
    fun providesCoroutineDispatcher() : CoroutineDispatcher = Dispatchers.IO

    //Get Google id
    @Provides
    @Singleton
    fun providesGoogleId(): GetGoogleIdOption =
        GetGoogleIdOption.Builder()
            .setServerClientId(OAUTH_ID)
            .setFilterByAuthorizedAccounts(false)
            .setAutoSelectEnabled(true)
            .build()

    //Request Google Credentials
    @Provides
    @Singleton
    fun providesCredentialRequest(
        googleIdOption: GetGoogleIdOption
    ): GetCredentialRequest = GetCredentialRequest
        .Builder()
        .addCredentialOption(googleIdOption)
        .build()

    //Clear Google credential upon sign out
    @Provides
    @Singleton
    fun providesClearCredentialsState(): ClearCredentialStateRequest =
        ClearCredentialStateRequest()

   //Credential Manager
   @Provides
   @Singleton
   fun provideCredentialManager(@ApplicationContext context: Context): androidx.credentials.CredentialManager {
       return androidx.credentials.CredentialManager.create(context)
   }

    //Firebase Auth
    @Provides
    @Singleton
    fun providesAuth(): FirebaseAuth = Firebase.auth


    @Provides
    fun providesLogin(
        auth: FirebaseAuth,
        credentialRequest: GetCredentialRequest,
        credentialManager: androidx.credentials.CredentialManager,
        clearCredentialStateRequest: ClearCredentialStateRequest
    ) : GoogleLoginRepository = GoogleLoginRepositoryImpl(auth, credentialRequest,credentialManager, clearCredentialStateRequest)


}