package com.flowerhop.kotlinandroidprac

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.flowerhop.kotlinandroidprac.database.AppDatabase
import com.flowerhop.kotlinandroidprac.mvvm.AnyViewModelFactory
import com.flowerhop.kotlinandroidprac.network.ApiClient
import com.flowerhop.kotlinandroidprac.repository.TodoItemRepository
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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

        GlobalScope.launch(Dispatchers.Main) {
            val gson = Gson()
            val apiClient = ApiClient()
            val userNames = apiClient.listUser().map {it.lastName}
            activity?.run { Toast.makeText(this, userNames.joinToString(", "), Toast.LENGTH_LONG).show() }
        }
    }
}