package com.example.inshortsclone.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inshortsclone.model.Data
import com.example.inshortsclone.model.DataDto
import com.example.inshortsclone.repository.NewsDataRepository
import kotlinx.coroutines.launch

class NewsDataViewModel(val repository: NewsDataRepository) : ViewModel() {
    val newsData: LiveData<DataDto>
        get() = repository.newsData

    fun getNews(category: String) {
        viewModelScope.launch {
            repository.getNews(category)
        }
    }

    fun addBookmark(data: Data) {
        viewModelScope.launch {
            repository.addBookmark(data)
        }
    }

    fun getBookmarkNews(): LiveData<List<Data>> {
        return repository.getBookmarkNews()
    }

    fun deleteBookmark(data: Data){
        viewModelScope.launch {
            repository.deleteBookmark(data)
        }
    }

}