package com.example.bislist.tasks

import android.view.View
import com.example.bislist.tasks.data.Task
import com.example.bislist.tasks.data.TaskActivity
import java.math.RoundingMode

class TaskDepoManger {

    private lateinit var taskCollection: MutableList<Task>

    var onTask: ((List<Task>) -> Unit)? = null
    var onTaskUpdate:((List<Task>) -> Unit)? = null
    var onTaskActivity: ((List<TaskActivity>) -> Unit)? = null

    fun loadTask() {

        taskCollection = mutableListOf(
                Task("Handle Liste",  tasks = mutableListOf(
                        TaskActivity("kjøp egg", true),
                        TaskActivity("kjøp juice", false),
                        TaskActivity("kjøp brød", false),
                        TaskActivity("kjøp appelsin", true),
                        TaskActivity("kjøp mjølk", false),
                        TaskActivity("kjøp kykkling", false),
                        TaskActivity("kjøp eple", true)
                ), progressStatus = 0),
                Task("Film liste", tasks = mutableListOf(
                        TaskActivity("Taken 4", false),
                        TaskActivity("jon wik ein", false),
                        TaskActivity("pirater av karibien 92", false)
                ), progressStatus = 0)
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

    fun removeTask(task: Task){
        taskCollection.remove(task)
        onTask?.invoke(taskCollection)
        updateTask()
    }
    fun removeActivity(task: Task, taskActivity: TaskActivity){
        task.tasks.remove(taskActivity)
        onTaskActivity?.invoke(task.tasks)
        updateTask()
    }

    fun progressTracker(task: Task){
        var sant = 0
        var tot = 0
        for (i in task.tasks){
            tot++
            if (i.state){
                sant++
            }
        }
        val prosent  =  sant/tot.toDouble() * 100
        println("tot var $tot")
        println("samt var $sant")
        println("prosent var $prosent")
        task.progressStatus = prosent.toBigDecimal().setScale(0, RoundingMode.UP).toInt()
        onTask?.invoke(taskCollection)
        updateTask()

    }

    companion object {
        val instance = TaskDepoManger()
    }

}