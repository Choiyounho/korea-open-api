package com.soten.openapi.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.soten.openapi.data.mapper.toModel
import com.soten.openapi.data.source.MovieLocalDataSource
import com.soten.openapi.data.source.MovieRemoteDataSource
import com.soten.openapi.data.source.paging.MovieRemoteMediator
import com.soten.openapi.domain.models.MovieModel
import com.soten.openapi.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
) : MovieRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getMovies(): Flow<PagingData<MovieModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                prefetchDistance = 15,
                enablePlaceholders = true
            ),
            remoteMediator = MovieRemoteMediator(
                movieRemoteDataSource = movieRemoteDataSource,
                movieLocalDataSource = movieLocalDataSource
            ),
            pagingSourceFactory = { movieLocalDataSource.getPagedMovies() }
        ).flow.map { pagingData ->
            pagingData.map { it.toModel() }
        }
    }
}