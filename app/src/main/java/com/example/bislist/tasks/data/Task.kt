package com.example.bislist.tasks.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Task(val titel:String, val state:Boolean): Parcelable

