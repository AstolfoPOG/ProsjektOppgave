package com.example.bislist.tasks.data

import android.icu.text.CaseMap
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TaskActivity(val activityName:String, val state:Boolean): Parcelable