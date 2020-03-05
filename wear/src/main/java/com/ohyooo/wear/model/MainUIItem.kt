package com.ohyooo.wear.model

import android.os.Parcelable
import androidx.databinding.ObservableField
import kotlinx.android.parcel.Parcelize

@Parcelize
class MainUIItem(val payload: ObservableField<String> = ObservableField("send a message first")) : Parcelable