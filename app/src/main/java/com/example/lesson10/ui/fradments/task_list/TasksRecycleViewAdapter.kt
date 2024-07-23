package com.example.lesson10.ui.fradments.task_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson10.R
import com.example.lesson10.db.Task

class TasksRecycleViewAdapter(var items: MutableList<Task> = mutableListOf()) : RecyclerView.Adapter<TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val listItemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(listItemView)
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.title
        holder.details.text = item.details
    }

    fun updateItems(itemsToUpdate:List<Task>){
        items = itemsToUpdate.toMutableList()
        notifyDataSetChanged()
    }
}

class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.task_title)
    val details: TextView = itemView.findViewById(R.id.task_details)
}