// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false // 📱 Android Application Plugin
    alias(libs.plugins.kotlin.android) apply false // 🟩 Kotlin Android Plugin
    alias(libs.plugins.kotlin.compose) apply false // 🎨 Kotlin Compose Plugin
    // 🔧 KSP (Kotlin Symbol Processing) plugin for annotation processing
    alias(libs.plugins.ksp) apply false // ✅ Use alias for KSP
    // 💉 Dagger Hilt Plugin for Dependency Injection
    alias(libs.plugins.hilt) apply false // ✅ Use alias for Hilt
}