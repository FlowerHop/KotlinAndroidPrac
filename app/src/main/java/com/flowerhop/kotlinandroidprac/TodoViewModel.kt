package com.flowerhop.kotlinandroidprac

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel:ViewModel() {
    val todosLiveData = MutableLiveData<List<Todo>>(
        mutableListOf(Todo.Title("Title"))
    )

    var count = 0;

    fun addTodo(name: String) {
        val list = todosLiveData.value!!.toMutableList().apply {
            add(Todo.Item("$name - $count", false))
            count++
        }

        todosLiveData.postValue(list)
    }
}