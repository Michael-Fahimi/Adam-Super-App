package com.madhan.adamsuperapp.auth

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SigninWithEmailAndPassword {
    companion object {
        private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()


        //  Sign up with Email & Password

        fun signUp(email: String, password: String, onResult: (FirebaseUser?) -> Unit) {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { user ->
                    if (user.isSuccessful) {
                        Log.d("EmailAuth", "Sign-up successful for $email")
                        onResult(firebaseAuth.currentUser)  // Returns user if successful
                    } else {
                        Log.e("EmailAuth", "Sign-up failed: ${user.exception?.message}")
                        onResult(null)  // Returns null if sign-up fails
                    }
                }
        }


         // Log In with Email & Password

        fun LogIn(email: String, password: String, onResult: (FirebaseUser?) -> Unit) {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { user ->
                    if (user.isSuccessful) {
                        Log.d("EmailAuth", "Sign-in successful for $email")
                        onResult(firebaseAuth.currentUser)  // Returns user if successful
                    } else {
                        Log.e("EmailAuth", "Sign-in failed: ${user.exception?.message}")
                        onResult(null)  // Returns null if sign-in fails
                    }
                }
        }


        //  Sign out the current user

        fun signOut() {
            firebaseAuth.signOut()
            Log.d("EmailAuth", "User signed out")
        }


        // Get the currently logged-in user

        fun getCurrentUser(): FirebaseUser? {
            return firebaseAuth.currentUser
        }


        //  Send password reset email

        fun resetPassword(email: String, onResult: (Boolean) -> Unit) {
            firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("PasswordRecovery", "Password reset email sent to: $email")
                        onResult(true)  // Returns true if email was sent
                    } else {
                        Log.e("PasswordRecovery", "Failed to send reset email: ${task.exception?.message}")
                        onResult(false)  // Returns false if reset failed
                    }
                }
        }
    }
}
