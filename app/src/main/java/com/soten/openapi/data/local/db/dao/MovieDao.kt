package com.soten.openapi.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.soten.openapi.data.local.db.entity.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieentity")
    suspend fun getAllMovies(): List<MovieEntity>

    @Insert
    suspend fun insertMovie(movieEntity: MovieEntity)

    @Insert
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("DELETE FROM movieentity WHERE code = :code")
    suspend fun deleteMovie(code: String)
}