package com.soten.openapi.di

import android.content.Context
import androidx.room.Room
import com.soten.openapi.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    private const val DB_NAME = "APP_DATABASE_NAME"

    @Singleton
    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()

    @Singleton
    @Provides
    fun providesMovieDao(appDatabase: AppDatabase) = appDatabase.movieDao()

}