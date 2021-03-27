package com.example.bislist.tasks

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bislist.EXTRA_TASK_INFO
import com.example.bislist.TaskHolder
import com.example.bislist.databinding.ActivityTaskDetailsBinding
import com.example.bislist.databinding.TaskLayoutBinding
import com.example.bislist.tasks.TaskActivityRecyclerAdapter
import com.example.bislist.tasks.data.Task
import com.example.bislist.tasks.data.TaskActivity
import kotlinx.android.synthetic.main.activity_task_details.*


class TaskDetailActivity : AppCompatActivity(){

    private lateinit var binding: ActivityTaskDetailsBinding
    private lateinit var task:Task
    private lateinit var taskActivity: TaskActivity
    private lateinit var taskActivityAdapter:TaskActivityRecyclerAdapter

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
        binding.title.text = task.taskTitle

        binding.activityListing.layoutManager = LinearLayoutManager(this)
        taskActivityAdapter = TaskActivityRecyclerAdapter(task.tasks)
        binding.activityListing.adapter = taskActivityAdapter

        TaskDepoManger.instance.onTaskActivity = {
            (binding.activityListing.adapter as TaskActivityRecyclerAdapter).updateTaskActivityCollection(it)
        }

        binding.saveBtActivity.setOnClickListener {

            val activityname = binding.activityName.text.toString()


            createActivity(activityname)

            val ipm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            ipm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }

    private fun createActivity(activityname: String){

        if(activityname.isNotEmpty()){
            val taskActivity = TaskActivity(activityname,false)
            TaskHolder.PickedTask?.let { TaskDepoManger.instance.addActivity(it, taskActivity) }
        }else{
            Toast.makeText(this, "enter text", Toast.LENGTH_SHORT).show()
        }
    }

}