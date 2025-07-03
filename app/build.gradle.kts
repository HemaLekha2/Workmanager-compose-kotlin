plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)

//    // STEP 2:
//    id("com.google.devtools.ksp") // 🔧 Kotlin Symbol Processing (KSP) Plugin
//    id("dagger.hilt.android.plugin") // 💉 Dagger Hilt Plugin for Dependency Injection
}

android {
    namespace = "com.example.workmanagerexample"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.workmanagerexample"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // ✅ Export Room schema
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true",
                    "room.expandProjection" to "true"
                )
            }
        }

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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.hilt.common)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // STEP 3
    // 💉 Hilt Dependency Injection
    implementation(libs.hilt.android) // 🚀 Hilt core library
    implementation(libs.androidx.hilt.navigation.compose) // 📦 Hilt Navigation for Compose
    ksp(libs.hilt.compiler) // 🏗️ Hilt Compiler for code generation
    // ✅ Room Database Setup (for local data storage)
    ksp(libs.androidx.room.compiler) // ⚙️ Room Database compiler for annotation processing
    implementation(libs.androidx.room.ktx) // 🔧 Room KTX extension for easier database handling
    implementation(libs.androidx.room.runtime) // 🛠️ Room runtime for database access

    // ✅ Add Room Paging Dependency (This fixes the error)
    implementation(libs.androidx.room.paging) // 🔥 Fix missing room-paging issue
    // ✅ Paging Library (Core)
    implementation(libs.androidx.paging.runtime.ktx)
    // ✅ Paging Support for Jetpack Compose
    implementation(libs.androidx.paging.compose)

    // Retrofit
    implementation(libs.retrofit)
// Retrofit with Gson converter
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")

}