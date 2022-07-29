package com.example.inshortsclone.api

import com.example.inshortsclone.model.DataDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiReference {

    @GET("/news")
    suspend fun getNews(
        @Query("category") category: String
    ): Response<DataDto>
}