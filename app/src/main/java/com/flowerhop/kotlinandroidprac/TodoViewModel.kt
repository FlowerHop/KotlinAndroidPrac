package com.flowerhop.kotlinandroidprac

import androidx.lifecycle.*
import com.flowerhop.kotlinandroidprac.database.TodoItem
import kotlinx.coroutines.launch
import java.util.*

class TodoViewModel(val todoItemRepository: TodoItemRepository) :ViewModel() {
    private val title = Todo.Title("This is a title")
    val todosLiveData = MediatorLiveData<List<Todo>>().apply {
        val source = todoItemRepository.getTodoItems().map {
            it.map { todoItem ->
                Todo.Item(
                    todoItem.id,
                    todoItem.title,
                    todoItem.done,
                    todoItem.createdAt
                )
            }
        }

        addSource(source) {
            this.value = mutableListOf(title) + it
        }

        value = mutableListOf(Todo.Title("Title"))
    }

    fun createNewTodo(title: String) {
        val todoItem = TodoItem(title, false, Date())
        viewModelScope.launch {
            todoItemRepository.insertTodoItem(todoItem)
        }
    }

    fun updateTodo(todo: Todo.Item) {
        val todoItem = TodoItem(
            todo.text,
            done = todo.checked,
            createdAt = todo.createdAt
        ).apply { id = todo.id }
        viewModelScope.launch {
            todoItemRepository.updateTodoItem(todoItem)
        }
    }
}