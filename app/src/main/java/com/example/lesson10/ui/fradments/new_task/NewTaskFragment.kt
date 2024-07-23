package com.example.lesson10.ui.fradments.new_task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.lesson10.R
import com.example.lesson10.db.Task
import com.example.lesson10.ui.fradments.TaskViewModel

class NewTaskFragment : Fragment() {
    private lateinit var viewModel: TaskViewModel

    companion object {
        fun newInstance() = NewTaskFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_new_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[TaskViewModel::class.java]
        val titleInputField: EditText = view.findViewById(R.id.new_title)
        val detailsInputField: EditText = view.findViewById(R.id.new_details)
        val addButton: Button = view.findViewById(R.id.add_task)
        addButton.setOnClickListener {
            val titLe = titleInputField.text.toString()
            val details = detailsInputField.text.toString()
            viewModel.addTask(Task(title = titLe, details = details))
            parentFragmentManager.popBackStack()
        }
    }
}