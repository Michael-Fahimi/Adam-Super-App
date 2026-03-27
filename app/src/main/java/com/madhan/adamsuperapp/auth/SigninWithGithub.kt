package com.madhan.adamsuperapp.auth

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.OAuthProvider
import com.google.firebase.auth.auth

class SigninWithGithub {
    companion object {
        fun signIn(activity: Activity, onSuccess: () -> Unit) { //Pass Activity and username
            Log.d("GithubAuth", "Login Running!")
            val firebaseAuth = Firebase.auth
            val provider = OAuthProvider.newBuilder("github.com")
            // Target specific username with login hint.
//            provider.addCustomParameter("login", username)
            val pendingResultTask = firebaseAuth.pendingAuthResult

            if (pendingResultTask != null) {
                // There's something already here! Finish the sign-in for your user.
                pendingResultTask
                    .addOnSuccessListener { authResult ->
                        Log.d("GithubAuth", "Login Success!")
                        // User is signed in.
                        // Handle the authResult
                        val credential = authResult.credential
                        if(credential is com.google.firebase.auth.OAuthCredential){
                            onSuccess()
                            val accessToken = credential.accessToken
                            val secret = credential.secret
                            Log.d("GithubAuth", "Access Token: $accessToken")
                            Log.d("GithubAuth", "Secret: $secret")
                        }
                    }
                    .addOnFailureListener {
                        Log.d("GithubAuth", "Login Failure: ${it.message}")
                        // Handle failure.
                    }
            } else {
                // There's no pending result so you need to start the sign-in flow.
                firebaseAuth.startActivityForSignInWithProvider(activity, provider.build())
                    .addOnSuccessListener { authResult ->
                        Log.d("GithubAuth", "Login Success!")
                        // User is signed in.
                        // Handle the authResult
                        val credential = authResult.credential
                        if(credential is com.google.firebase.auth.OAuthCredential){
                            onSuccess()
                            val accessToken = credential.accessToken
                            val secret = credential.secret
                            Log.d("GithubAuth", "Access Token: $accessToken")
                            Log.d("GithubAuth", "Secret: $secret")
                        }

                    }
                    .addOnFailureListener {
                        Log.d("GithubAuth", "Login Failure: ${it.message}")
                        // Handle failure.
                    }
            }
        }
    }
}

fun Context.findActivity(): Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    return null
}