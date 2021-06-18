package com.flowerhop.kotlinandroidprac

import androidx.lifecycle.ViewModel

class TodoViewModel:ViewModel() {
    var todos = mutableListOf<Todo>().apply {
        this.add(Todo.Title("Title"))
    }
    var count = 0;

    fun addTodo(name: String) {
        todos = todos.toMutableList().apply {
            add(Todo.Item("$name - $count", false))
            count++
        }
    }
}