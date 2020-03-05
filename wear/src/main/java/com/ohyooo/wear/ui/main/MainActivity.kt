package com.ohyooo.wear.ui.main

import android.os.Bundle
import com.ohyooo.lib.extension.viewDataBindingOf
import com.ohyooo.lib.extension.viewModelOf
import com.ohyooo.lib.mvvm.MVVMBaseActivity
import com.ohyooo.wear.R
import com.ohyooo.wear.databinding.ActivityMainBinding
import com.ohyooo.wear.viewmodel.MainViewModel

class MainActivity : MVVMBaseActivity(R.layout.activity_main) {

    val mViewModel by viewModelOf<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = viewDataBindingOf<ActivityMainBinding>()
        db.vm = mViewModel
    }
}