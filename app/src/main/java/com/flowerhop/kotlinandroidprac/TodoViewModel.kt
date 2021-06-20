package com.flowerhop.kotlinandroidprac

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel:ViewModel() {
    val addTodoIntent = MutableLiveData<String>()
    val todosLiveData = MediatorLiveData<List<Todo>>().apply {
        addSource(addTodoIntent) {
            val list = value!!.toMutableList().apply {
                add(Todo.Item("${addTodoIntent.value}", false))
            }

            value = list
        }

        value = mutableListOf(Todo.Title("Title"))
    }
}