package com.flowerhop.kotlinandroidprac

import java.util.*

sealed class Todo(val viewType: Int) {
    data class Title(val text: String): Todo(VIEW_TYPE_TITLE)
    data class Item(val id: Int,
                    val text:String,
                    val checked:Boolean,
                    val createdAt: Date
    ): Todo(VIEW_TYPE_ITEM)

    companion object {
        const val VIEW_TYPE_TITLE = 0
        const val VIEW_TYPE_ITEM = 1
    }
}