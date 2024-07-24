package com.example.lesson10.ui.fradments.new_task

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lesson10.db.Task

class NewTaskViewModel : ViewModel() {
    val valueToUpdate = MutableLiveData<Task?>(null)
    fun setCurrentData(title: String? = null, details: String? = null) {
        val currentTask: Task? = if (title != null && details != null) {
            Task(title = title, details = details)
        } else {
            null
        }
        valueToUpdate.value = currentTask
    }
}