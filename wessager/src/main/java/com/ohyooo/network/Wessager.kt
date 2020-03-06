package com.ohyooo.network

import android.content.Context
import android.util.Log
import com.google.android.gms.wearable.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import java.util.concurrent.ConcurrentHashMap

object Wessager : DataClient.OnDataChangedListener {
    private lateinit var context: Context

    private lateinit var type: Type

    private val register = ConcurrentHashMap<Long, (String) -> Unit>()

    fun init(context: Context, type: Type) {
        this.context = context
        this.type = type
        Wearable.getDataClient(context).addListener(this)
    }

    fun exit() {
        Wearable.getDataClient(context).removeListener(this)
        listeners.clear()
        register.clear()
    }

    suspend fun send(msg: String
                     , waitResponse: Boolean = false
                     , sessionId: Long = System.currentTimeMillis()
                     , timeout: Long = 10000): String? = withContext(Dispatchers.IO) {
        val resp = withTimeoutOrNull(timeout) {
            var result: String? = null
            val mutex = Mutex(waitResponse)
            if (waitResponse) {
                register[sessionId] = {
                    result = it
                    mutex.unlock()
                }
            }
            Wearable.getDataClient(context).putDataItem(getRequest(msg, sessionId, timeout))
            mutex.withLock { result }
        }
        if (resp == null && waitResponse) {
            register.remove(sessionId)
        }
        return@withContext resp
    }

    private fun getRequest(msg: String, sessionId: Long, timeout: Long): PutDataRequest {
        val request = PutDataMapRequest.create("${Constants.PREFIX}/${type.name}")
        request.dataMap.putLong(Constants.SESSION_ID, sessionId)
        request.dataMap.putLong(Constants.EXPIRE_TIME, sessionId + timeout)
        request.dataMap.putString(Constants.PAYLOAD, msg)
        return request.asPutDataRequest().setUrgent()
    }

    override fun onDataChanged(dataEvents: DataEventBuffer) {
        dataEvents.filter { it.type == DataEvent.TYPE_CHANGED }
                .filter { it.dataItem.uri.lastPathSegment != type.name }
                .forEach { event ->
                    DataMapItem.fromDataItem(event.dataItem).dataMap.apply {
                        val sessionId = getLong(Constants.SESSION_ID)
                        val expireTime = getLong(Constants.EXPIRE_TIME)
                        if (sessionId <= 0 || expireTime < System.currentTimeMillis()) {
                            Log.e("Wessager", String.format("received expired: sessionId=%s", sessionId))
                            return
                        }
                        val payload = getString(Constants.PAYLOAD)
                        Log.e("Wessager", String.format("received: sessionId=%s payload=%s", sessionId, payload))
                        if (register.containsKey(sessionId)) {
                            register[sessionId]!!.invoke(payload)
                            register.remove(sessionId)
                        } else {
                            broadcastMessage(payload, sessionId)
                        }
                    }
                }
    }

    private val listeners = ArrayList<OnMessageReceivedListener>()

    fun addListener(listener: OnMessageReceivedListener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener)
        }
    }

    fun removeListener(listener: OnMessageReceivedListener) {
        listeners.remove(listener)
    }

    private fun broadcastMessage(msg: String, sessionId: Long) {
        listeners.forEach { it.onMessageReceived(msg, sessionId) }
    }

}
