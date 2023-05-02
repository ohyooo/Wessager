package com.ohyooo.network

fun interface OnMessageReceivedListener {
    fun onMessageReceived(msg: String, sessionId: Long)
}
