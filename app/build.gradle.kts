plugins {
    alias(libs.plugins.doraPlug.android.application)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.doraPlug.android.compose)
    alias(libs.plugins.doraPlug.android.hilt)
    alias(libs.plugins.doraPlug.android.room)
}

android {
    namespace = "com.univ.doraboda_compose"

    defaultConfig {
        applicationId = "com.univ.doraboda_compose"
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
}

dependencies {
    //module
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":feature:calendar"))
    implementation(project(":core:ui"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //navigation
    implementation(libs.navigation)
    androidTestImplementation(libs.navigation.testing) //Testing

    //serialization
    implementation(libs.kotlinx.serialization.json)

    //constraint layout
    implementation(libs.androidx.constraintLayout)

    //hilt-navigation
    implementation(libs.hilt.navigation)
}