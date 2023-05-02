package com.ohyooo.wear.ui.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.ohyooo.wear.ui.main.MainActivity

class SplashActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
