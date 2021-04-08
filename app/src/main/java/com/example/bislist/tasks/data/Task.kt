package com.example.bislist.tasks.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize


data class Task(val taskTitle: String, var tasks:MutableList<TaskActivity>, var progressStatus: Int): Parcelable