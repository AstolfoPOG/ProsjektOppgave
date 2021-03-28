package com.example.bislist.tasks

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import com.example.bislist.TaskHolder
import com.example.bislist.databinding.ActivityTaskDetailsBinding
import com.example.bislist.databinding.TaskActivityLayoutBinding
import com.example.bislist.tasks.data.Task
import com.example.bislist.tasks.data.TaskActivity


class TaskActivityRecyclerAdapter(private var taskActivitys:List<TaskActivity>, private val onActivityClicked:(TaskActivity) -> Unit) : RecyclerView.Adapter<TaskActivityRecyclerAdapter.ViewHolder>(){

    class ViewHolder(val binding: TaskActivityLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(taskActivity: TaskActivity, onActivityClicked: (TaskActivity) -> Unit) {
            binding.activityName.text = taskActivity.activityName

            binding.removeActivityBt.setOnClickListener {
                TaskHolder.PickedTask?.let { it1 -> TaskDepoManger.instance.removeActivity(task = it1, taskActivity = taskActivity)
                }
            }
            binding.card2.setOnClickListener {
                onActivityClicked(taskActivity)
                if (taskActivity.state){
                binding.card2.setCardBackgroundColor(Color.parseColor("#556B2F"))
                }else{
                    binding.card2.setCardBackgroundColor(Color.parseColor("#8B0000"))
                }
            }
        }
    }

    override fun getItemCount(): Int = taskActivitys.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val taskActivity = taskActivitys[position]
        holder.bind(taskActivity,onActivityClicked)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TaskActivityLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    public fun updateTaskActivityCollection(newTaskActivity: List<TaskActivity>) {
        taskActivitys = newTaskActivity
        notifyDataSetChanged()
    }
}