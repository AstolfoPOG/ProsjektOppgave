package com.example.bislist.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bislist.databinding.ActivityTaskDetailsBinding
import com.example.bislist.databinding.TaskActivityLayoutBinding
import com.example.bislist.tasks.data.Task
import com.example.bislist.tasks.data.TaskActivity


class TaskActivityRecyclerAdapter(private var taskActivitys:List<TaskActivity>) : RecyclerView.Adapter<TaskActivityRecyclerAdapter.ViewHolder>(){

    class ViewHolder(val binding: TaskActivityLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(taskActivity: TaskActivity) {
            binding.activityName.text = taskActivity.activityName

        }
    }

    override fun getItemCount(): Int = taskActivitys.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val taskActivity = taskActivitys[position]
        holder.bind(taskActivity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TaskActivityLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    public fun updateTaskActivityCollection(newTaskActivity: List<TaskActivity>) {
        taskActivitys = newTaskActivity
        notifyDataSetChanged()
    }
}