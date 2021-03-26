package com.example.bislist.tasks

import android.widget.Toast
import com.example.bislist.tasks.data.Task

class TaskDepoManger {

    private lateinit var taskCollection: MutableList<Task>

    var onTask: ((List<Task>) -> Unit)? = null
    var onTaskUpdate:((task:Task) -> Unit)? = null

    fun loadTask() {

        taskCollection = mutableListOf(
                Task("Handle Liste",false),
                Task("Film Liste",  false),
                Task("test1",  false),
                Task("test2", false),
                Task("test3", false),
                Task("test4", false),
                Task("test5",  false),
                Task("test6",  false),
                Task("test7", false),
                Task("test8", false),
                Task("test9",  false),
                Task("Huske Liste", false)
        )


        onTask?.invoke(taskCollection)
    }

    fun updateTask(task: Task){
        onTaskUpdate?.invoke(task)
    }


    fun addTask(task: Task){
        taskCollection.add(task)
        onTask?.invoke(taskCollection)
    }

    companion object {
        val instance = TaskDepoManger()
    }

}