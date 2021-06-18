package com.flowerhop.kotlinandroidprac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
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
        adapter.refresh(listOf<TodoItem>(
            TodoItem("todoItem 1", true),
            TodoItem("todoItem 2", true),
            TodoItem("todoItem 3", true),
            TodoItem("todoItem 4", true),
            TodoItem("todoItem 5", true),
            TodoItem("todoItem 6", true),
            TodoItem("todoItem 7", true),
            TodoItem("todoItem 8", true),
        ))
    }
}

class TodoListAdapter: RecyclerView.Adapter<TodoItemViewHolder>() {
    var todoItems = listOf<TodoItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        return TodoItemViewHolder(parent)
    }

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        holder.bind(todoItems[position])
    }

    override fun getItemCount(): Int {
        return todoItems.size
    }

    fun refresh(todoItems: List<TodoItem>) {
        this.todoItems = todoItems
    }
}

data class TodoItem (
    val text:String,
    val checked:Boolean
)

class TodoItemViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
) {
    fun bind(todoItem: TodoItem) {
        val checkBox =  itemView as AppCompatCheckBox
        checkBox.text = todoItem.text
        checkBox.isChecked = todoItem.checked
    }
}