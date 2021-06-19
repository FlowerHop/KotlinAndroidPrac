package com.flowerhop.kotlinandroidprac

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.recyclerview.widget.RecyclerView

class TodoItemViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
) {
    fun bind(todoItem: Todo.Item) {
        val checkBox = itemView as AppCompatCheckBox
        checkBox.text = todoItem.text
        checkBox.isChecked = todoItem.checked
    }
}