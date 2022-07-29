package com.example.inshortsclone.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarked_news")
data class Data(
    @PrimaryKey(autoGenerate = true)
    val newsId: Int,
    val author: String,
    val content: String,
    val date: String,
    val imageUrl: String,
    val readMoreUrl: String,
    val time: String,
    val title: String,
    val url: String
)