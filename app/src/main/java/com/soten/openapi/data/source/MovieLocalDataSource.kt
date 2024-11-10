package com.soten.openapi.data.source

import com.soten.openapi.data.local.db.dao.MovieDao
import com.soten.openapi.data.local.db.dao.RemoteKeyDao
import com.soten.openapi.data.local.db.entity.MovieEntity
import com.soten.openapi.data.local.db.entity.RemoteKey
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(
    private val movieDao: MovieDao,
    private val remoteKeyDao: RemoteKeyDao
) {

    suspend fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    suspend fun deleteAllMovies() = movieDao.clearAll()

    fun getPagedMovies() = movieDao.getPagedMovies()

    suspend fun getRemoteKey() = remoteKeyDao.getRemoteKey()

    suspend fun insertOrUpdate(remoteKey: RemoteKey) = remoteKeyDao.insertOrUpdate(remoteKey)

    suspend fun clearRemoteKeys() = remoteKeyDao.clearRemoteKeys()
}