package com.soten.openapi.data.mapper

import com.soten.openapi.data.local.db.entity.MovieEntity
import com.soten.openapi.data.remote.response.MovieItemResponse
import com.soten.openapi.domain.models.MovieModel

fun MovieModel.toEntity(): MovieEntity {
    return MovieEntity(
        code = code,
        movieName = movieName,
        openDate = openDate,
        genreName = genreName,
    )
}

fun MovieEntity.toModel(): MovieModel {
    return MovieModel(
        code = code,
        movieName = movieName,
        openDate = openDate,
        genreName = genreName,
    )
}

fun MovieItemResponse.toEntity(): MovieEntity {
    return MovieEntity(
        code = code,
        movieName = movieName,
        openDate = openDate,
        genreName = genreName,
    )
}

fun MovieItemResponse.toModel(): MovieModel {
    return MovieModel(
        code = code,
        movieName = movieName,
        openDate = openDate,
        genreName = genreName,
    )
}