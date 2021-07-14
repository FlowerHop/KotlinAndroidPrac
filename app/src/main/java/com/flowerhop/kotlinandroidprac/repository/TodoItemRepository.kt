package com.flowerhop.kotlinandroidprac.repository

import androidx.lifecycle.LiveData
import com.flowerhop.kotlinandroidprac.database.AppDatabase
import com.flowerhop.kotlinandroidprac.database.TodoItem

class TodoItemRepository(private val database: AppDatabase) {
    suspend fun insertTodoItem(todoItem: TodoItem) {
        database.todoItemDao().insert(todoItem)
    }

    suspend fun updateTodoItem(todoItem: TodoItem) {
        database.todoItemDao().update(todoItem)
    }

    fun getTodoItems(): LiveData<List<TodoItem>> {
        return database.todoItemDao().findAll()
    }
}