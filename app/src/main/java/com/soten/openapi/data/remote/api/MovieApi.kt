package com.soten.openapi.data.remote.api

import com.soten.openapi.data.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET(ApiInfo.EndPoints.SEARCH)
    suspend fun getMovies(
        @Query("curPage") page: Int = DEFAULT_PAGE,
        @Query("key") key: String = ApiInfo.KEY,
        @Query("itemPerPage") itemPerPage: Int = DEFAULT_ITEM_PER_PAGE
    ): MovieResponse

    companion object {
        const val DEFAULT_PAGE = 1
        const val DEFAULT_ITEM_PER_PAGE = 30
    }
}