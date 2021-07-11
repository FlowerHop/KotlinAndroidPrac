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
import com.flowerhop.kotlinandroidprac.database.AppDatabase
import com.flowerhop.kotlinandroidprac.mvvm.AnyViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment: Fragment(R.layout.fragment_main) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val todoItemDB = AppDatabase.getInstance(requireContext().applicationContext)
        val todoItemRepository = TodoItemRepository(todoItemDB)
        val todoViewModelFactory = AnyViewModelFactory{
            TodoViewModel(todoItemRepository)
        }
        val todoViewModel = ViewModelProvider(requireActivity(), todoViewModelFactory).get(TodoViewModel::class.java)
        val adapter = TodoListAdapter().apply {
            onTouchChangeListener = object : OnTouchChangeListener {
                override fun onChange(todo: Todo.Item) {
                    todoViewModel.updateTodo(todo)
                }
            }
        }

        todoViewModel.todosLiveData.observe(viewLifecycleOwner, Observer { todos: List<Todo> -> adapter.submitList(todos) })
        todoList.adapter = adapter
        todoList.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        todoList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        button.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToAddTodoFragment("Memo"))
        }
    }
}