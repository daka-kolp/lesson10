package com.example.lesson10.ui.fradments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lesson10.CustomApplication
import com.example.lesson10.logic.Task

class TaskViewModel : ViewModel() {
    private val repo = CustomApplication.getApp().repo
    private val _listState = MutableLiveData<ListState>(ListState.EmptyList())
    val listState: LiveData<ListState> = _listState

    init {
        repo.getTasks {
            _listState.value = ListState.UpdatedList(it)
        }
        repo.onTasksUpdated {
            _listState.value = ListState.UpdatedList(it)
        }
    }


    fun addTask(newTask: Task) {
        repo.addTask(newTask)
    }

    sealed class ListState {
        class EmptyList : ListState()
        class UpdatedList(val list: List<Task>) : ListState()
    }
}