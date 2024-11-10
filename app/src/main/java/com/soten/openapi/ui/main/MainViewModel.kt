package com.soten.openapi.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.soten.openapi.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    private val _error = MutableSharedFlow<Throwable?>()
    val error = _error.asSharedFlow()

    val movies = movieRepository.getMovies()
        .catch { _error.emit(it) }
        .cachedIn(viewModelScope)
}