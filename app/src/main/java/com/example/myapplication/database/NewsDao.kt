package com.example.myapplication.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NewsDao {

    @Insert
    suspend fun insert(news: News)

    @Delete
    suspend fun delete(news: News)

    @Query("SELECT * FROM News")
    fun getAllNews():List<News>

    @Query("SELECT * FROM News WHERE id=:id")
    fun getOne(id:Int): News
}