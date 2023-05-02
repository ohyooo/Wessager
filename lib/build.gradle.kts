plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    namespace = "com.ohyooo.lib"
    compileSdk = Ext.compileSdk
    buildToolsVersion = Ext.buildToolsVersion
    defaultConfig {
        minSdk = Ext.minSdk
        // versionCode = Ext.versionCode
        // versionName = Ext.versionName
        consumerProguardFiles("consumer-rules.pro")
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
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.fragment:fragment-ktx:1.5.6")
}
