package com.flowerhop.kotlinandroidprac.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface TodoItemDao: GenericDao<TodoItem> {
    @Query("SELECT * FROM ${TodoItem.TABLE_NAME} ORDER BY ${TodoItem.COLUMN_CREATED_AT} DESC")
    fun findAll(): LiveData<List<TodoItem>>
}