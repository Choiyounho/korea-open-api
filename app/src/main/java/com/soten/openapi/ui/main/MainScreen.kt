package com.soten.openapi.ui.main

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.soten.openapi.domain.models.MovieModel
import retrofit2.HttpException

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val movies = mainViewModel.movies.collectAsLazyPagingItems()

    when (val error = mainViewModel.error.collectAsState().value) {
        null -> Unit
        is HttpException -> handleHttpException(context, error)
        else -> handleUnknownException(context, error)
    }

    LazyColumn(
        modifier = modifier
    ) {
        items(
            count = movies.itemCount,
        ) {
            MovieItem(
                movie = movies[it]!!,
                onMovieClick = {}
            )
        }
    }
}


@Composable
fun MovieItem(
    movie: MovieModel,
    onMovieClick: (String) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Text(
            text = "영화 이름: ${movie.movieName}",
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "장르 ${movie.genreName}",
            fontSize = 15.sp
        )
    }
}


fun handleHttpException(context: Context, error: HttpException) {
    val errorMessage = when (error.code()) {
        400 -> "잘못된 요청입니다."
        401 -> "인증이 필요합니다."
        403 -> "접근이 거부되었습니다."
        404 -> "요청한 데이터를 찾을 수 없습니다."
        500 -> "서버 오류가 발생했습니다."
        502 -> "게이트웨이 오류가 발생했습니다."
        503 -> "서비스를 사용할 수 없습니다."
        else -> "알 수 없는 오류가 발생했습니다."
    }

    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
}

fun handleUnknownException(context: Context, error: Throwable) {
    Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
}