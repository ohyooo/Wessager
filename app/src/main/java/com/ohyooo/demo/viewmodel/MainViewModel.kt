package com.ohyooo.demo.viewmodel

import android.icu.text.SimpleDateFormat
import androidx.lifecycle.viewModelScope
import com.ohyooo.demo.model.MainUIItem
import com.ohyooo.lib.mvvm.MVVMBaseViewModel
import com.ohyooo.network.Wessager
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*

class MainViewModel : MVVMBaseViewModel() {
    val ui = MainUIItem()

    override fun onCreate() {
        Wessager.addListener { msg, sessionId ->
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault())
            val date = simpleDateFormat.format(Date(sessionId))
            ui.payload.set("received: $msg\n$date")
            viewModelScope.launch {
                Wessager.send("received", false, sessionId)
            }
        }
    }

    fun sendToWear() {
        viewModelScope.launch {
            val resp = Wessager.send("send from phone", true)
            Timber.e(resp)
        }
    }

}