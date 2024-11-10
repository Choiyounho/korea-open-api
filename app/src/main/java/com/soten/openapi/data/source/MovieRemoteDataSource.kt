package com.soten.openapi.data.source

import com.soten.openapi.data.remote.api.MovieApi
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val movieApi: MovieApi
){

    suspend fun getMovies(page: Int = MovieApi.DEFAULT_PAGE) =
        movieApi.getMovies(page)
}