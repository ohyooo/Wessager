object Ext {
    const val applicationId = "com.ohyooo.qrscan"
    const val compileSdk = 33
    const val buildToolsVersion = "33.0.2"
    const val minSdk = 21
    const val targetSdk = 33
    const val versionCode = 8
    const val versionName = "2.8"
}

object Libs {
    val updateList = arrayListOf<String>()
    val implementList = arrayListOf<String>()
    val debugImplementList = arrayListOf<String>()

    object Versions {
        const val kotlin = "1.8.21"
        const val agp = "8.0.1"
    }

    object Plugin {
        val AGP = "com.android.tools.build:gradle:${Versions.agp}".regUpdate()
        val KGP = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}".regUpdate()
        val KS = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}".regUpdate()
    }

    object Kotlin {
        val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4".regLib()
    }

    object Google {
        val wearable = "com.google.android.gms:play-services-wearable:18.0.0".regLib()
    }

    object AndroidX {
        val appcompat = "androidx.appcompat:appcompat:1.6.1".regLib()
        val coreKtx = "androidx.core:core-ktx:1.10.0".regLib()
        val fragment = "androidx.fragment:fragment-ktx:1.5.6".regLib()
        //
        val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0".regLib()
    }

    object Others {
        val timber = "com.jakewharton.timber:timber:5.0.1".regLib()
    }


    init {
        Plugin
        Kotlin
        AndroidX
        Google
    }

    private fun String.regLib() = this.also {
        implementList.add(it)
        updateList.add(it)
    }
    fun String.regDebug() = this.also {
        debugImplementList.add(it)
        updateList.add(it)
    }
    private fun String.regUpdate() = this.also(updateList::add)
}
