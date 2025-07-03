// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false // 📱 Android Application Plugin
    alias(libs.plugins.kotlin.android) apply false // 🟩 Kotlin Android Plugin
    alias(libs.plugins.kotlin.compose) apply false // 🎨 Kotlin Compose Plugin

    // STEP 1:
    // 🔧 KSP (Kotlin Symbol Processing) plugin for annotation processing
    id("com.google.devtools.ksp")

    // 💉 Dagger Hilt Plugin for Dependency Injection
    id("com.google.dagger.hilt.android")  apply false
}