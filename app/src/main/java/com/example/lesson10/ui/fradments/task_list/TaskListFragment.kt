package com.example.lesson10.ui.fradments.task_list

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson10.R
import com.example.lesson10.ui.fradments.TaskViewModel
import com.example.lesson10.ui.fradments.new_task.NewTaskFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TaskListFragment : Fragment() {
    private lateinit var viewModel: TaskViewModel
    private var adapter: TasksRecycleViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_task_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fab = view.findViewById<FloatingActionButton>(R.id.add_task_fab)
        fab.setOnClickListener { setNewTaskClickedListener() }
        val orientation = resources.configuration.orientation
        fab.isVisible = orientation != Configuration.ORIENTATION_LANDSCAPE

        val tasksRecyclerView: RecyclerView = view.findViewById(R.id.tasks)
        tasksRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel = ViewModelProvider(requireActivity())[TaskViewModel::class.java]
        adapter = TasksRecycleViewAdapter()
        tasksRecyclerView.adapter = adapter
        viewModel.listState.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is TaskViewModel.ListState.EmptyList -> Unit
                is TaskViewModel.ListState.UpdatedList -> {
                    adapter?.updateItems(uiState.list)
                }
            }
        }
    }

    private fun setNewTaskClickedListener() {
        val newTaskFragmentToAdd = NewTaskFragment.newInstance()
        getParentFragmentManager().beginTransaction()
            .add(R.id.task_list, newTaskFragmentToAdd)
            .addToBackStack("NewTaskFragment")
            .commit()
    }
}