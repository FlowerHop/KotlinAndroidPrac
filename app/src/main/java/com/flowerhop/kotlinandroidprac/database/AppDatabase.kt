package com.flowerhop.kotlinandroidprac.database

import android.content.Context
import androidx.room.*

@Database(
    version = 1,
    entities = [
        TodoItem::class
    ],
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    companion object {
        private const val DATABASE_NAME = "todo_list_db"

        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
        }
    }

    abstract fun todoItemDao(): TodoItemDao
}