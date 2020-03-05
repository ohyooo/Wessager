package com.ohyooo.wear.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class Header(var timestamp: Long = System.currentTimeMillis(), val headers: HashMap<String, String> = HashMap()) : Parcelable

@Parcelize
data class Data(var header: Header = Header(), var body: String? = null) : Parcelable {
    fun toJson(): String {
        val headerObj = JSONObject()
        headerObj.put(header::timestamp.name, header.timestamp)

        val mapObj = JSONObject(header.headers as Map<*, *>)
        headerObj.put(header::headers.name, mapObj)

        val dataObj = JSONObject()
        dataObj.put(::header.name, headerObj)
        dataObj.put(::body.name, body)

        return dataObj.toString()
    }

    fun fromJson(json: String?): Data {
        json ?: return this
        val jsonObj = JSONObject(json)
        body = jsonObj.getString(::body.name)

        val headerObj = jsonObj.getJSONObject(::header.name)
        val mapObj = headerObj.getJSONObject(header::headers.name)
        mapObj.keys().forEach {
            header.headers[it] = mapObj.getString(it)
        }
        return this
    }
}