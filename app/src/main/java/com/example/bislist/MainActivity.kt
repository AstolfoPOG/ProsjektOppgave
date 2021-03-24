package com.example.bislist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bislist.databinding.ActivityMainBinding
import com.example.bislist.tasks.TaskCollectionAdapter
import com.example.bislist.tasks.data.Task

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var taskAdapter:TaskCollectionAdapter

    private val taskCollection:MutableList<Task> = mutableListOf(
            Task("Handle Liste"),
            Task("Film Liste"),
            Task("Huske Liste")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.taskListing.layoutManager = LinearLayoutManager(this)
        binding.taskListing.adapter = TaskCollectionAdapter(taskCollection)
    }
}