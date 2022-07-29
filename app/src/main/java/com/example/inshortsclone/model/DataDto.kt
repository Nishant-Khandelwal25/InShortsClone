package com.example.inshortsclone.model

data class DataDto(
    val category: String,
    val data: List<Data>,
    val success: Boolean
)