package com.soten.openapi.data.source

import com.soten.openapi.data.remote.api.MovieApi
import com.soten.openapi.data.remote.response.MovieResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retryWhen
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val movieApi: MovieApi
){

    fun getMovies(page: Int = MovieApi.DEFAULT_PAGE): Flow<MovieResponse> {
        return flow {
            emit(movieApi.getMovies(page))
        }.retryWhen { _, attempt ->
            delay(1000 * attempt)
            true
        }
    }
}