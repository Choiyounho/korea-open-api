package com.soten.openapi.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey
    val code: String,
    val movieName: String,
    val openDate: String,
    val endDate: String,
    val genreName: String,
    val price: Int,
)