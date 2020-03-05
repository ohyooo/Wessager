package com.ohyooo.wear.app

import android.app.Application
import com.ohyooo.network.Type
import com.ohyooo.network.Wessager
import timber.log.Timber

class App : Application() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Wessager.init(this, Type.WEAR)
    }

    companion object {
        lateinit var instance: App
            private set
    }
}