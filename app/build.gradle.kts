plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.gms.google-services") //firebase google service id
    id("com.google.firebase.crashlytics") //firebase crashlytics id
    id("com.google.devtools.ksp") //ksp
    id("dagger.hilt.android.plugin") //hilt
}

android {
    namespace = "com.madhan.adamsuperapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.madhan.adamsuperapp"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(project(":core"))
    implementation(project(":feature-bank"))
    implementation(project(":feature-delivery"))
    implementation(project(":feature-hotel"))
    implementation(project(":feature-pet"))
    implementation(project(":feature-tinder"))
    implementation(project(":feature-uber"))

    //Firebase
    implementation(platform(libs.firebase.bom))
    //Firebase Analytics
    implementation(libs.firebase.analytics)
    //Firebase Crashlytics
    implementation(libs.firebase.crashlytics)
    //Hilt
    implementation(libs.hilt.android)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.googleid)
    implementation(libs.androidx.navigation.compose)
    ksp(libs.hilt.android.compiler)
    //viewmodel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    //implementation(libs.firebase.auth.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

}