package com.example.bislist.tasks

import android.view.View
import androidx.core.net.toUri
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.example.bislist.tasks.data.Task
import com.example.bislist.tasks.data.TaskActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import java.io.*
import java.math.RoundingMode

class TaskDepoManger {

    private lateinit var taskCollection: MutableList<Task>

    var onTask: ((List<Task>) -> Unit)? = null
    var onTaskUpdate:((List<Task>) -> Unit)? = null
    var onTaskActivity: ((List<TaskActivity>) -> Unit)? = null

    fun loadTask(filePath: File?, id: String) {

        val file = File(filePath,id.plus(".json"))
        TaskService.instance.download(file.toUri())

        val reader = JsonReader(FileReader(file))

        val gson = Gson()
        val typeDef = object : TypeToken<MutableList<Task>>() {}.type
        val jsonList = gson.fromJson<MutableList<Task>>(reader,typeDef)
        taskCollection = jsonList

        onTask?.invoke(taskCollection)
        reader.close()
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
        progressTracker(task)
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
        if (sant > 0 && tot > 0) {
            val prosent = sant / tot.toDouble() * 100
            task.progressStatus = prosent.toBigDecimal().setScale(0, RoundingMode.UP).toInt()
        }else{
            task.progressStatus = 0
        }
        onTask?.invoke(taskCollection)
        updateTask()

    }


    fun saveFile(filePath: File?, id: String){
        val gson = GsonBuilder().setPrettyPrinting().create()
        val jsonTaskList: String = gson.toJson(taskCollection)
        val fileName = id.plus(".json")
        val file = File(filePath,fileName)
        File(filePath,fileName).writeText(jsonTaskList)
        TaskService.instance.upload(file.toUri())

    }

    companion object {
        val instance = TaskDepoManger()
    }

}