package com.example.lesson10.logic

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.UUID

class TaskRepo(private val context: Context) {
    private val database = FirebaseDatabase.getInstance(
        "https://todolist-14d3a-default-rtdb.europe-west1.firebasedatabase.app/"
    )

    fun getTasks(onSuccess: (todoList: List<Task>) -> Unit) {
        val target = database.reference
            .child(getAccount()?.id ?: "unknown")
            .child("todos")

        target.get().addOnCompleteListener { task ->
            val todoList = mutableListOf<Task>()
            if (task.isSuccessful) {
                task.result.children.forEach {
                    val todo = it.getValue(Task::class.java)
                    if (todo != null) todoList.add(todo)
                }
                onSuccess(todoList)
            }
        }
    }

    fun onTasksUpdated(onUpdate: (todoList: List<Task>) -> Unit) {
        val target = database.reference
            .child(getAccount()?.id ?: "unknown")
            .child("todos")

        target.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val todoList = mutableListOf<Task>()
                snapshot.children.forEach {
                    val todo = it.getValue(Task::class.java)
                    if (todo != null) todoList.add(todo)
                }
                onUpdate(todoList)
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }


    fun addTask(newTask: Task) {
        val target = database.reference
            .child(getAccount()?.id ?: "unknown")
            .child("todos")
            .child(UUID.randomUUID().toString())

        target.setValue(newTask).addOnCompleteListener {}
    }

    private fun getAccount(): GoogleSignInAccount? {
        return GoogleSignIn.getLastSignedInAccount(context)
    }
}