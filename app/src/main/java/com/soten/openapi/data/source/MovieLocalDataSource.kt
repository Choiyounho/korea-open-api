package com.soten.openapi.data.source

import androidx.room.withTransaction
import com.soten.openapi.data.local.db.AppDatabase
import com.soten.openapi.data.local.db.entity.MovieEntity
import com.soten.openapi.data.local.db.entity.RemoteKey
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(
    private val appDatabase: AppDatabase,
) {

    suspend fun insertMovies(movies: List<MovieEntity>) = appDatabase.movieDao().insertMovies(movies)

    suspend fun deleteAllMovies() = appDatabase.movieDao().clearAll()

    fun getPagedMovies() = appDatabase.movieDao().getPagedMovies()

    suspend fun getRemoteKey() = appDatabase.remoteKeyDao().getRemoteKey()

    suspend fun insertOrUpdate(remoteKey: RemoteKey) = appDatabase.remoteKeyDao().insertOrUpdate(remoteKey)

    suspend fun clearRemoteKeys() = appDatabase.remoteKeyDao().clearRemoteKeys()

    suspend fun withTransaction(block: suspend () -> Unit) = appDatabase.withTransaction(block)
}