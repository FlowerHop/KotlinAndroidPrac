package com.flowerhop.kotlinandroidprac

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoTitleViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_title, parent, false)
) {
    fun bind(todoTitle: Todo.Title) {
        val textView = itemView as TextView
        textView.text = todoTitle.text
    }
}