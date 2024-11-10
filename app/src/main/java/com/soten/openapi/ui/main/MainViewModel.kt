package com.soten.openapi.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.soten.openapi.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    private val _error = MutableStateFlow<Throwable?>(null)
    val error = _error.asStateFlow()

    val movies = movieRepository.getMovies()
        .cachedIn(viewModelScope)
        .catch { _error.value = it }
}