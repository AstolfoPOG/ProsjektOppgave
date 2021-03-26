package com.example.bislist.tasks

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.bislist.EXTRA_TASK_INFO
import com.example.bislist.TaskHolder
import com.example.bislist.databinding.ActivityTaskDetailsBinding
import com.example.bislist.databinding.TaskLayoutBinding
import com.example.bislist.tasks.data.Task


class TaskDetailActivity : AppCompatActivity(){

    private lateinit var binding: ActivityTaskDetailsBinding
    private lateinit var task:Task

    override fun onCreate(savedInstance: Bundle?){
        super.onCreate(savedInstance)
        binding = ActivityTaskDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val receivedTask = TaskHolder.PickedTask

        if(receivedTask != null){
            task = receivedTask
            Log.i("Details view", receivedTask.toString())
        }else{
            setResult(Activity.RESULT_CANCELED, Intent(EXTRA_TASK_INFO).apply {
                //t
            })
            finish()
        }
        binding.title.text = task.titel
    }



}