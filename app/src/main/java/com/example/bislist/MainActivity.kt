package com.example.bislist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bislist.databinding.ActivityMainBinding
import com.example.bislist.tasks.TaskActivityDepoManger
import com.example.bislist.tasks.TaskRecyclerAdapter
import com.example.bislist.tasks.TaskDepoManger
import com.example.bislist.tasks.TaskDetailActivity
import com.example.bislist.tasks.data.Task
import com.example.bislist.tasks.data.TaskActivity


const val EXTRA_TASK_INFO: String = "com.example.bislist.task.info"

class TaskHolder{

    companion object{
        var PickedTask:Task? = null
    }

}

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.taskListing.layoutManager = LinearLayoutManager(this)
        binding.taskListing.adapter = TaskRecyclerAdapter(emptyList<Task>(), this::onTaskClicked)

        TaskDepoManger.instance.onTask = {
            (binding.taskListing.adapter as TaskRecyclerAdapter).updateCollection(it)
        }

        TaskDepoManger.instance.loadTask()

        binding.saveBt.setOnClickListener {
            val title = binding.title.text.toString()

            binding.title.setText("")

            addTask(title)
        }


    }


    private fun addTask(title: String){
        val task = Task(title, tasks = mutableListOf())

        if (title.isNotEmpty())
            TaskDepoManger.instance.addTask(task)
        else{
            Toast.makeText(this, "enter text", Toast.LENGTH_SHORT).show()
        }


    }




    private fun onTaskClicked(task: Task): Unit{


        TaskHolder.PickedTask = task

        val intent = Intent(this, TaskDetailActivity::class.java)

        startActivity(intent)

    }

}