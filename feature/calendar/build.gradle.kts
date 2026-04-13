plugins {
    alias(libs.plugins.doraPlug.library)
    alias(libs.plugins.doraPlug.library.compose)
    alias(libs.plugins.doraPlug.android.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.univ.calendar"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
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
    //modules
    implementation(project(":domain"))
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
}