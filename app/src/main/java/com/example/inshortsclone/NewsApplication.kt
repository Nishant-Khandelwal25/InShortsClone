package com.example.inshortsclone

import android.app.Application
import androidx.room.Room
import com.example.inshortsclone.api.ApiReference
import com.example.inshortsclone.api.RetrofitHelper
import com.example.inshortsclone.repository.NewsDataRepository
import com.example.inshortsclone.room.NewsDatabase

class NewsApplication : Application() {

    lateinit var newsDataRepository: NewsDataRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val apiReference = RetrofitHelper.getInstance().create(ApiReference::class.java)
        val database = NewsDatabase.getDatabase(applicationContext)
        newsDataRepository = NewsDataRepository(apiReference, database)
    }
}