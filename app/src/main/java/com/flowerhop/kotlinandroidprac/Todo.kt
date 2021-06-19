package com.flowerhop.kotlinandroidprac

sealed class Todo(val viewType: Int) {
    data class Title(val text: String): Todo(VIEW_TYPE_TITLE)
    data class Item(val text:String,
                    val checked:Boolean): Todo(VIEW_TYPE_ITEM)

    companion object {
        const val VIEW_TYPE_TITLE = 0
        const val VIEW_TYPE_ITEM = 1
    }
}