// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false // 📱 Android Application Plugin
    alias(libs.plugins.kotlin.android) apply false // 🟩 Kotlin Android Plugin
    alias(libs.plugins.kotlin.compose) apply false // 🎨 Kotlin Compose Plugin
    // STEP 1:
    // 🔧 KSP (Kotlin Symbol Processing) plugin for annotation processing
    alias(libs.plugins.ksp) apply false // ✅ Use alias, not id()
    // 💉 Dagger Hilt Plugin for Dependency Injection
    alias(libs.plugins.hilt) apply false
    // 🛠️ Kotlin KAPT (Kotlin Annotation Processing Tool) for Room and Hilt
    alias(libs.plugins.kapt) apply false // ✅ now kapt works via alias

 //   id("com.google.dagger.hilt.android") apply false // 💉 Dagger Hilt Plugin for Dependency Injection
//    id("com.google.devtools.ksp") apply false // 🔧 Kotlin Symbol Processing (KSP) Plugin

 //   id("org.jetbrains.kotlin.kapt") apply false

}