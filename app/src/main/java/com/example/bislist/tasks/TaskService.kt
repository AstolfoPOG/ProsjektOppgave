package com.example.bislist.tasks

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import com.example.bislist.MainActivity
import com.example.bislist.tasks.data.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import java.io.File
import java.io.FileReader

class TaskService{

    private lateinit var auth: FirebaseAuth
    private val TAG:String = "bisList:TaskService"

    fun signIn(){

        auth = Firebase.auth
        signInAnon()
    }

    private fun signInAnon(){
        auth.signInAnonymously().addOnSuccessListener {
            Log.d(TAG,"Login success ${it.user.toString()}")
        }.addOnFailureListener {
            Log.e(TAG, "Login failed", it)
        }

    }

    fun upload(file:Uri){
        val ref = FirebaseStorage.getInstance().reference.child("list/${file.lastPathSegment}")
        val uploadTask = ref.putFile(file)

        uploadTask.addOnSuccessListener {
            Log.d(TAG, "Lagret fil ${it.toString()}")
        }.addOnFailureListener {
            Log.e(TAG, "Error i lagring", it)
        }
    }
    fun download(filePath: File?, id: String){
        val file = File(filePath,id.plus(".json")).toUri()

        val ref = FirebaseStorage.getInstance().reference.child("list/${file.lastPathSegment}")
        val downloadTask = ref.getFile(file)

        downloadTask.addOnSuccessListener {
            val readerFile = File(filePath,id.plus(".json"))
            val reader = JsonReader(FileReader(readerFile))


            if(it != null){
                val gson = Gson()

                val typeDef = object : TypeToken<MutableList<Task>>() {}.type
                val jsonList = gson.fromJson<MutableList<Task>>(reader,typeDef)

                if(jsonList != null){
                    TaskDepoManger.instance.taskCollection = jsonList
                }else{
                    TaskDepoManger.instance.loadTask()
                }

                reader.close()
                TaskDepoManger.instance.updater()
            }else{
                TaskDepoManger.instance.loadTask()
            }

        }.addOnFailureListener {
            Log.e(TAG, "Error i henting av fil", it)
            TaskDepoManger.instance.loadTask()
        }

    }
    companion object {
        val instance = TaskService()
    }
}