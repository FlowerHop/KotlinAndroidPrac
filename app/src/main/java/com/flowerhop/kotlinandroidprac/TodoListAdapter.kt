package com.flowerhop.kotlinandroidprac

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

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