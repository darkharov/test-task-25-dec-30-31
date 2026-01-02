import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "${Configs.APPLICATION_ID}.commons.resources"
    compileSdk {
        version = release(Configs.Sdk.COMPILE)
    }

    defaultConfig {
        minSdk = Configs.Sdk.MIN
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = Configs.Java.ENUM_ENTRY
        targetCompatibility = Configs.Java.ENUM_ENTRY
    }

    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.fromTarget(Configs.Java.JVM_TARGET)
        }
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    api(libs.androidx.activity.compose)
    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.graphics)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.material3)
    api(libs.androidx.material.icons.extended)
    debugApi(libs.androidx.compose.ui.tooling)
}
