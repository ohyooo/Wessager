plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    namespace = "com.ohyooo.wear"
    compileSdk = Ext.compileSdk
    buildToolsVersion = Ext.buildToolsVersion
    defaultConfig {
        minSdk = Ext.minSdk
        targetSdk = Ext.targetSdk
        versionCode = Ext.versionCode
        versionName = Ext.versionName
        // consumerProguardFiles("consumer-rules.pro")
    }
    buildTypes {
        named("debug") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        named("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation(project(":lib"))
    implementation(project(":wessager"))
    compileOnly("com.google.android.wearable:wearable:2.9.0")
    implementation("com.google.android.gms:play-services-wearable:18.0.0")
    implementation("com.jakewharton.timber:timber:5.0.1")
}
