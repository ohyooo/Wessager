package com.ohyooo.network

import android.content.Context
import androidx.startup.Initializer

class ContextInitializer : Initializer<Unit> {
    override fun create(c: Context) {
        context = c
    }

    override fun dependencies() = emptyList<Class<Initializer<*>>>()

    companion object{
        lateinit var context:Context
    }
}