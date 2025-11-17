plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    // essential for Firebase
    id("com.google.gms.google-services")

    // next line necessary for the Jsonify interface
    kotlin("plugin.serialization") version "2.2.21"





}

android {
    buildFeatures {
        compose=true
    }
    namespace = "com.example.sharingapp"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.sharingapp"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables.useSupportLibrary = true
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

    // necessary for compose runtime
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }

}

dependencies {

    // for using google fonts
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)        // ADD THIS LINE
    implementation("androidx.compose.ui:ui-text-google-fonts:1.9.3")


    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.text.google.fonts)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3.adaptive.navigation.suite)
    implementation(libs.androidx.room.common.jvm)
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.junit.junit)
    testImplementation(libs.junit.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    // needed to use setContent in MainActivity
    val activity_version = "1.11.0"
    implementation("androidx.activity:activity-compose:${activity_version}")
    // Java language implementation
    implementation("androidx.activity:activity:${activity_version}")
    // Kotlin
    implementation("androidx.activity:activity-ktx:${activity_version}")

    // for being able to use the Material api
    implementation("androidx.compose.material:material:1.9.4")

    // this line is necessarry for the Jsonify interface
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.foundation)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // added
    // errors occurred, so I commented these out for now
//    androidTestImplementation(platform(libs.androidx.compose.bom))
//    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
//    debugImplementation(libs.androidx.compose.ui.tooling)
//    debugImplementation(libs.androidx.compose.ui.test.manifest)
//    implementation(libs.androidx.fragment.ktx)

    implementation(libs.androidx.appcompat)

    implementation(libs.androidx.appcompat)
    implementation("com.google.android.material:material:1.13.0")
    implementation("androidx.coordinatorlayout:coordinatorlayout:1.3.0")
    implementation("androidx.viewpager:viewpager:1.1.0")

    // below dependencies are for FireStore database usage
    implementation(platform("com.google.firebase:firebase-bom:34.5.0"))
    implementation("com.google.firebase:firebase-analytics")

    // needed for reading and writing json files
    implementation("com.google.code.gson:gson:2.13.2")

    // needed for Compose
    implementation(libs.androidx.runtime)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.runtime.rxjava2)

    // The next two dependencies are needed for using fragments

    // Java language implementation
    implementation(libs.androidx.fragment)
    // Kotlin
    implementation(libs.androidx.fragment.ktx)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

}

