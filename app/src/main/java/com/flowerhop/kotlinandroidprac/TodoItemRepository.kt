package com.flowerhop.kotlinandroidprac

import androidx.lifecycle.LiveData
import com.flowerhop.kotlinandroidprac.database.AppDatabase
import com.flowerhop.kotlinandroidprac.database.TodoItem

class TodoItemRepository(private val database: AppDatabase) {
    suspend fun insertTodoItem(todoItem: TodoItem) {
        database.todoItemDao().insert(todoItem)
    }

    fun getTodoItems(): LiveData<List<TodoItem>> {
        return database.todoItemDao().findAll()
    }
}