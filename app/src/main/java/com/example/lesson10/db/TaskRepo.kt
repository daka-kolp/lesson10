package com.example.lesson10.db

import java.util.concurrent.Executors

class TaskRepo(private val database: TaskDb) {
    private val executor = Executors.newSingleThreadExecutor()

    fun getTasks() = database.taskDao().getAll()

    fun addTask(newTask: Task) {
        executor.execute { database.taskDao().add(newTask) }
    }

    fun removeTask(task: Task) {
        executor.execute { database.taskDao().delete(task) }
    }
}