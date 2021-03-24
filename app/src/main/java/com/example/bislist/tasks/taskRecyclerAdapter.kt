package com.example.bislist.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bislist.databinding.TaskLayoutBinding
import com.example.bislist.tasks.data.Task

class TaskCollectionAdapter(private val tasks:MutableList<Task>) : RecyclerView.Adapter<TaskCollectionAdapter.ViewHolder>(){

    class ViewHolder(val binding:TaskLayoutBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.title.text = task.titel
        }
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TaskLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    public fun updateCollection(newTasks:MutableList<Task>){
        tasks.clear()
        tasks.addAll(newTasks)
        notifyDataSetChanged()
    }

}

