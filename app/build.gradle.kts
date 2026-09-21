plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.godlike.picsort"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.godlike.picsort"
        minSdk = 29
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    implementation(platform(libs.compose.bom))
    androidTestImplementation(platform(libs.compose.bom))

    // Material Design 3
    implementation(libs.compose.material3)
    implementation(libs.compose.material.icons.extended)

    // Foundation
    implementation(libs.compose.foundation)

    // Compose UI
    implementation(libs.compose.ui)

    // Preview Support
    implementation(libs.compose.ui.tooling.preview)
    debugImplementation(libs.compose.ui.tooling)

    // Activity Integration
    implementation(libs.activity.compose)

    // ViewModel Integration
    implementation(libs.lifecycle.viewmodel.compose)

    // Android Google Fonts
    implementation(libs.androidx.ui.text.google.fonts)
}