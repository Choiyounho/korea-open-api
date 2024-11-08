package com.soten.openapi.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    @SerialName("movieListResult")
    val movieListResponse: MovieListResponse
)

@Serializable
data class MovieListResponse(
    @SerialName("totCnt") val totalCount: Int,
    @SerialName("source") val source: String,
    @SerialName("movieList") val movieItemsResponse: List<MovieItemResponse>,
)

@Serializable
data class MovieItemResponse(
    @SerialName("movieCd") val code: String,
    @SerialName("movieNm") val movieName: String,
    @SerialName("movieNmEn") val englishName: String,
    @SerialName("prdtYear") val productionYear: String,
    @SerialName("openDt") val openDate: String,
    @SerialName("typeNm") val type: String,
    @SerialName("prdtStatNm") val productionStatus: String,
    @SerialName("nationAlt") val nationList: String,
    @SerialName("genreAlt") val genreList: String,
    @SerialName("repNationNm") val nationName: String,
    @SerialName("repGenreNm") val genreName: String,
    @SerialName("directors") val directorResponses: List<DirectorResponse> = emptyList(),
    @SerialName("companys") val companies: List<CompanyResponse> = emptyList(),
)

@Serializable
data class DirectorResponse(
    @SerialName("peopleNm") val name: String,
)

@Serializable
data class CompanyResponse(
    @SerialName("companyNm") val name: String,
)