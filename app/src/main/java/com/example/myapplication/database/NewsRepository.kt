package com.example.myapplication.database

class NewsRepository(
    private val db:NewsDatabase
) {
    suspend fun insert(news:News) = db.getNewsDao().insert(news)
    suspend fun delete(news:News) = db.getNewsDao().delete(news)

    fun getAllNews():List<News> = db.getNewsDao().getAllNews()
}