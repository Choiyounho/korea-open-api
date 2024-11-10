package com.soten.openapi.data.source.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.soten.openapi.data.local.db.entity.MovieEntity
import com.soten.openapi.data.local.db.entity.RemoteKey
import com.soten.openapi.data.mapper.toEntity
import com.soten.openapi.data.remote.api.MovieApi.Companion.DEFAULT_PAGE
import com.soten.openapi.data.source.MovieLocalDataSource
import com.soten.openapi.data.source.MovieRemoteDataSource
import okio.IOException
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
) : RemoteMediator<Int, MovieEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>,
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> DEFAULT_PAGE

                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)

                LoadType.APPEND -> {
                    val remoteKey = movieLocalDataSource.getRemoteKey()
                    val nextKey = remoteKey?.nextKey
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    nextKey
                }
            }
            val responseToEntities =
                movieRemoteDataSource.getMovies(page = page).movieListResponse.movieItemsResponse.map { it.toEntity() }

            movieLocalDataSource.let {
                if (loadType == LoadType.REFRESH) {
                    it.deleteAllMovies()
                    it.clearRemoteKeys()
                }
                it.insertMovies(responseToEntities)
                it.insertOrUpdate(RemoteKey(nextKey = page + 1, lastUpdated = System.currentTimeMillis()))
            }

            MediatorResult.Success(endOfPaginationReached = responseToEntities.isEmpty())
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}