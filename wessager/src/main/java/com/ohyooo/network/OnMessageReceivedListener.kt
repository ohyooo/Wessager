package com.ohyooo.network

interface OnMessageReceivedListener {
    fun onMessageReceived(msg: String, sessionId: Long)
}
