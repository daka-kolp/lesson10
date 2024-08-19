package com.example.lesson10.logic

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Task(
    val title: String? = null,
    val details: String? = null,
)