plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    namespace = "com.ohyooo.wessager"
    compileSdk = Ext.compileSdk
    buildToolsVersion = Ext.buildToolsVersion
    defaultConfig {
        minSdk = Ext.minSdk
        // versionCode = Ext.versionCode
        // versionName = Ext.versionName
        // consumerProguardFiles("consumer-rules.pro")
    }
    buildTypes {
        named("debug") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        named("release") {
            isMinifyEnabled = true
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
}

dependencies {
    implementation("androidx.startup:startup-runtime:1.1.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("com.google.android.gms:play-services-wearable:18.0.0")
}
