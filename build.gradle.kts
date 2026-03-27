// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.dynamic.feature) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    id("com.google.gms.google-services") version "4.4.2" apply false //firebase google service
    alias(libs.plugins.google.firebase.crashlytics) apply false //firebase crashlytics
    id("com.google.devtools.ksp") version "2.0.21-1.0.27" //
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
}
buildscript {
    dependencies {
        // ...
        classpath("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.1")
    }
}