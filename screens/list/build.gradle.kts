import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.google.hilt.android)
}

android {
    namespace = "${Configs.APPLICATION_ID}.list"
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
    implementation(project(":commons:compose"))
    implementation(project(":commons:resources"))

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.hilt.compiler)
    ksp(libs.kotlin.metadata.jvm)
}
