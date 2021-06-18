package com.flowerhop.kotlinandroidprac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = TodoListAdapter()
        todoList.adapter = adapter
        todoList.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        todoList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter.refresh(listOf<Todo>(
            Todo.Title("Title 1"),
            Todo.Item("todoItem 1", true),
            Todo.Item("todoItem 2", true),
            Todo.Item("todoItem 3", true),
            Todo.Item("todoItem 4", true),
            Todo.Title("Title 2"),
            Todo.Item("todoItem 5", true),
            Todo.Item("todoItem 6", true),
            Todo.Item("todoItem 7", true),
            Todo.Item("todoItem 8", true),
            Todo.Item("todoItem 8", true),
            Todo.Item("todoItem 9", true),
            Todo.Item("todoItem 10", true),
            Todo.Item("todoItem 11", true),
            Todo.Item("todoItem 12", true),
        ))
    }
}

class TodoListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var todos = listOf<Todo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            Todo.VIEW_TYPE_TITLE -> TodoTitleViewHolder(parent)
            else -> TodoItemViewHolder(parent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return todos[position].viewType
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val todo = todos[position]) {
            is Todo.Title -> (holder as TodoTitleViewHolder).bind(todo)
            is Todo.Item -> (holder as TodoItemViewHolder).bind(todo)
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    fun refresh(todos: List<Todo>) {
        this.todos = todos
    }
}

sealed class Todo(val viewType: Int) {
    data class Title(val text: String): Todo(VIEW_TYPE_TITLE)
    data class Item(val text:String,
                    val checked:Boolean): Todo(VIEW_TYPE_ITEM)

    companion object {
        const val VIEW_TYPE_TITLE = 0
        const val VIEW_TYPE_ITEM = 1
    }
}

class TodoTitleViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_title, parent, false)
) {
    fun bind(todoTitle: Todo.Title) {
        val textView = itemView as TextView
        textView.text = todoTitle.text
    }
}

class TodoItemViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
) {
    fun bind(todoItem: Todo.Item) {
        val checkBox = itemView as AppCompatCheckBox
        checkBox.text = todoItem.text
        checkBox.isChecked = todoItem.checked
    }
}