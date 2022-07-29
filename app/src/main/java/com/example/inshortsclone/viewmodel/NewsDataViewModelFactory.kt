package com.example.inshortsclone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.inshortsclone.repository.NewsDataRepository

class NewsDataViewModelFactory(private val repository: NewsDataRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsDataViewModel(repository) as T
    }
}