package com.example.bislist.tasks

import android.text.style.TextAppearanceSpan
import android.widget.Toast
import com.example.bislist.tasks.data.Task
import com.example.bislist.tasks.data.TaskActivity

class TaskDepoManger {

    private lateinit var taskCollection: MutableList<Task>

    var onTask: ((List<Task>) -> Unit)? = null
    var onTaskUpdate:((List<Task>) -> Unit)? = null
    var onTaskActivity: ((List<TaskActivity>) -> Unit)? = null

    fun loadTask() {

        taskCollection = mutableListOf(
                Task("Handle Liste", tasks = mutableListOf(
                        TaskActivity("kjøp egg", false),
                        TaskActivity("kjøp juice", false),
                        TaskActivity("kjøp eple", false)
                )),
                Task("Film liste", tasks = mutableListOf(
                        TaskActivity("Taken 4", false),
                        TaskActivity("jon wik ein", false),
                        TaskActivity("pirater av karibien 92", false)
                ))
        )


        onTask?.invoke(taskCollection)
    }

    fun updateTask(){
        onTaskUpdate?.invoke(taskCollection)
    }


    fun addTask(task: Task){
        taskCollection.add(task)
        onTask?.invoke(taskCollection)
    }

    fun addActivity(task: Task, taskActivity: TaskActivity){
        task.tasks.add(taskActivity)
        onTaskActivity?.invoke(task.tasks)
        updateTask()
    }

    companion object {
        val instance = TaskDepoManger()
    }

}