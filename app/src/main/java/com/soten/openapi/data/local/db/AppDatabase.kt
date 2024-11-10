package com.soten.openapi.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.soten.openapi.data.local.db.dao.MovieDao
import com.soten.openapi.data.local.db.dao.RemoteKeyDao
import com.soten.openapi.data.local.db.entity.MovieEntity
import com.soten.openapi.data.local.db.entity.RemoteKey

@Database(entities = [MovieEntity::class, RemoteKey::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}