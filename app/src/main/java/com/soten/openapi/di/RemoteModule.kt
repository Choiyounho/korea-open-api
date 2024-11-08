package com.soten.openapi.di

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.soten.openapi.data.remote.api.ApiInfo
import com.soten.openapi.data.remote.api.MovieApi
import com.soten.openapi.data.remote.interceptor.ApiInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    private const val CONTENT_TYPE = "application/json"
    private const val LOG_TAG = "Kobis-Movie"

    private val networkJson: Json = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(ApiInfo.BASE_URL)
            .addConverterFactory(networkJson.asConverterFactory(CONTENT_TYPE.toMediaType()))
            .client(
                provideOkHttpClient(
                    ApiInterceptor(),
                    HttpLoggingInterceptor { log ->
                        Log.d(LOG_TAG, log)
                    }.apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(): MovieApi {
        return provideRetrofit().create(MovieApi::class.java)
    }

    private fun provideOkHttpClient(vararg interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder().run {
            interceptor.forEach { addInterceptor(it) }
            build()
        }
    }
}