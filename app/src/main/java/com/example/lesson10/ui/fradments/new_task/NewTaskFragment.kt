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
    private lateinit var innerViewModel: NewTaskViewModel
    private var titleInputField: EditText? = null
    private var detailsInputField: EditText? = null

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
        innerViewModel = ViewModelProvider(requireActivity())[NewTaskViewModel::class.java]
        titleInputField = view.findViewById(R.id.new_title)
        detailsInputField = view.findViewById(R.id.new_details)

        activity?.let {
            innerViewModel.valueToUpdate.observe(it) { task ->
                titleInputField?.setText(task?.title ?: "")
                detailsInputField?.setText(task?.details ?: "")
            }
        }

        val addButton: Button = view.findViewById(R.id.add_task)
        addButton.setOnClickListener {
            val title = titleInputField?.text.toString()
            val details = detailsInputField?.text.toString()
            viewModel.addTask(Task(title = title, details = details))
            innerViewModel.setCurrentData()
            parentFragmentManager.popBackStack()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        innerViewModel.setCurrentData(
            titleInputField?.text.toString(),
            detailsInputField?.text.toString(),
        )
    }
}