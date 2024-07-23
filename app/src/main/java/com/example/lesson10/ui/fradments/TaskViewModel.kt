package com.example.lesson10.ui.fradments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.lesson10.CustomApplication
import com.example.lesson10.db.Task

class TaskViewModel : ViewModel() {
    private val repo = CustomApplication.getApp().repo
    private val _listState = MutableLiveData<ListState>(ListState.EmptyList())
    val listState: LiveData<ListState> = _listState
    private val observer = Observer<List<Task>> {
        _listState.postValue(ListState.UpdatedList(list = it))
    }

    init {
        repo.getTasks().observeForever(observer)
    }

    fun addTask(newTask: Task) = repo.addTask(newTask)

    fun removeTask(task: Task) = repo.removeTask(task)

    override fun onCleared() {
        repo.getTasks().removeObserver(observer)
        super.onCleared()
    }

    sealed class ListState {
        class EmptyList : ListState()
        class UpdatedList(val list: List<Task>) : ListState()
    }
}