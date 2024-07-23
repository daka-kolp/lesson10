package com.example.lesson10.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {
    @Insert
    fun add(task: Task)
    @Delete
    fun delete(task: Task)
    @Query("SELECT * FROM task")
    fun getAll(): LiveData<List<Task>>
}