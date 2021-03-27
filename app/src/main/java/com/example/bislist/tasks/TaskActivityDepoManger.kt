package com.example.bislist.tasks

import android.content.Context
import com.example.bislist.tasks.data.Task
import com.example.bislist.tasks.data.TaskActivity

class TaskActivityDepoManger{

    private lateinit var taskActivityCollection: MutableList<TaskActivity>

    var onTaskActivity: ((List<TaskActivity>) -> Unit)? = null

    fun loadActivity(context: Context){

        taskActivityCollection = mutableListOf()

        onTaskActivity?.invoke(taskActivityCollection)
    }




    companion object {
        val instance = TaskActivityDepoManger()
    }
}