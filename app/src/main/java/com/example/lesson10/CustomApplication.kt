package com.example.lesson10

import android.app.Application
import com.example.lesson10.logic.TaskRepo

class CustomApplication : Application() {
    lateinit var repo: TaskRepo
    override fun onCreate() {
        super.onCreate()
        instance = this
        repo = TaskRepo(applicationContext)
    }

    companion object {
        private lateinit var instance: CustomApplication
        fun getApp() = instance
    }
}