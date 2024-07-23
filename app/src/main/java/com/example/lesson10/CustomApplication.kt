package com.example.lesson10

import android.app.Application
import androidx.room.Room
import com.example.lesson10.db.TaskRepo
import com.example.lesson10.db.TaskDb

class CustomApplication : Application() {
    lateinit var repo: TaskRepo
    override fun onCreate() {
        super.onCreate()
        instance = this
        val db = Room.databaseBuilder(this, TaskDb::class.java, "task_database").build()
        repo = TaskRepo(db)
    }

    companion object {
        private lateinit var instance: CustomApplication
        fun getApp() = instance
    }
}