package com.flowerhop.kotlinandroidprac

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment: Fragment(R.layout.fragment_main) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TodoListAdapter()
        todoList.adapter = adapter
        todoList.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        todoList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val todoViewModel = ViewModelProvider(requireActivity()).get(TodoViewModel::class.java)
        todoViewModel.todosLiveData.observe(viewLifecycleOwner, Observer { todos: List<Todo> -> adapter.submitList(todos) })

        button.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addTodoFragment)
        }
    }
}