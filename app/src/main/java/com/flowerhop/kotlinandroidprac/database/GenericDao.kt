package com.flowerhop.kotlinandroidprac.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

interface GenericDao<T> {
    @Insert
    fun insert(obj: T)

    @Insert
    fun insert(vararg obj: T)

    @Update
    fun update(obj: T)

    @Delete
    fun delete(obj: T)
}