package com.flowerhop.kotlinandroidprac.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = TodoItem.TABLE_NAME
)
data class TodoItem (
    @ColumnInfo(name = COLUMN_TITLE) var title: String,
    @ColumnInfo(name = COLUMN_DONE) var done: Boolean,
    @ColumnInfo(name = COLUMN_CREATED_AT) var createdAt: Date) {

    companion object {
        const val TABLE_NAME = "todo_items"

        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_DONE = "done"
        const val COLUMN_CREATED_AT = "created_at"
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID) var id: Int = 0
}