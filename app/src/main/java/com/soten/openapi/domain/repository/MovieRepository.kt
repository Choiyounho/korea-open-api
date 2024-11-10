package com.soten.openapi.domain.repository

import androidx.paging.PagingData
import com.soten.openapi.domain.models.MovieModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovies(): Flow<PagingData<MovieModel>>
}