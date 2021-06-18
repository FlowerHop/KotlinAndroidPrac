package com.flowerhop.kotlinandroidprac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.*
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = TodoListAdapter()
        todoList.adapter = adapter
        todoList.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        todoList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
        todoViewModel.todosLiveData.observe(this, Observer { todos: List<Todo> -> adapter.submitList(todos) })

        button.setOnClickListener {
            todoViewModel.addTodoIntent.postValue("Item")
        }
    }
}

class TodoListAdapter: ListAdapter<Todo, RecyclerView.ViewHolder>(
    object : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.viewType == newItem.viewType
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            Todo.VIEW_TYPE_TITLE -> TodoTitleViewHolder(parent)
            else -> TodoItemViewHolder(parent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val todo = this.getItem(position)) {
            is Todo.Title -> (holder as TodoTitleViewHolder).bind(todo)
            is Todo.Item -> (holder as TodoItemViewHolder).bind(todo)
        }
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