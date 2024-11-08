package com.soten.openapi.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.soten.openapi.data.local.db.dao.MovieDao
import com.soten.openapi.data.local.db.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao
}