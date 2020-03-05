package com.ohyooo.wear.viewmodel

import android.icu.text.SimpleDateFormat
import androidx.lifecycle.viewModelScope
import com.ohyooo.lib.mvvm.MVVMBaseViewModel
import com.ohyooo.network.OnMessageReceivedListener
import com.ohyooo.network.Wessager
import com.ohyooo.wear.model.MainUIItem
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.Date

class MainViewModel : MVVMBaseViewModel() {

    val ui = MainUIItem()

    override fun onCreate() {
        Wessager.addListener(object : OnMessageReceivedListener {
            override fun onMessageReceived(msg: String, sessionId: Long) {
                val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                val date = simpleDateFormat.format(Date(sessionId))
                ui.payload.set("received: $msg\n$date")
                // viewModelScope.launch {
                // Wessager.send("received", false, sessionId)
                // }
            }
        })
    }

    fun sendMessage() {
        viewModelScope.launch {
            val response = Wessager.send("send from wear", true)
            Timber.e(response)
        }
    }
}