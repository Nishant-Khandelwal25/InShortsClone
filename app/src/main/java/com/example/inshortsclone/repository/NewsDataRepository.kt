package com.example.inshortsclone.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.inshortsclone.api.ApiReference
import com.example.inshortsclone.model.Data
import com.example.inshortsclone.model.DataDto
import com.example.inshortsclone.room.NewsDatabase

class NewsDataRepository(
    private val apiReference: ApiReference,
    private val newsDatabase: NewsDatabase
) {
    private val _newsLiveData = MutableLiveData<DataDto>()
    private val _bookmarkNewsLiveData = MutableLiveData<List<DataDto>>()

    val newsData: LiveData<DataDto>
        get() = _newsLiveData

    val bookmarkNewsData: LiveData<List<DataDto>>
        get() = _bookmarkNewsLiveData

    suspend fun getNews(category: String) {
        val result = apiReference.getNews(category)
        if (result.body() != null) {
            _newsLiveData.postValue(result.body())
        }
    }

    suspend fun addBookmark(data: Data) {
        newsDatabase.newsDao().addNote(data)
    }

    suspend fun deleteBookmark(data: Data){
        newsDatabase.newsDao().deleteNote(data)
    }

    fun getBookmarkNews(): LiveData<List<Data>> {
        return newsDatabase.newsDao().getNews()
    }
}