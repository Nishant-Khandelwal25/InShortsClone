package com.example.inshortsclone.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.inshortsclone.model.Data
import retrofit2.http.DELETE

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(data: Data)

    @Query("SELECT * from bookmarked_news")
    fun getNews(): LiveData<List<Data>>

    @Delete
    suspend fun deleteNote(data: Data)
}