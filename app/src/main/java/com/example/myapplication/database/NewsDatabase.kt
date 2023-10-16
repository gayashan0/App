package com.example.myapplication.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = [News::class], version = 1)
abstract class NewsDatabase:RoomDatabase() {

    abstract fun getNewsDao():NewsDao
    companion object {
        @Volatile
        private var INSTANCE: NewsDatabase? = null
        fun getInstance(context: Context): NewsDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context,
                    NewsDatabase::class.java,
                    "news_db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}