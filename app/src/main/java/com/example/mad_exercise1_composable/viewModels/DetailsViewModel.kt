package com.example.mad_exercise1_composable.viewModels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_exercise1_composable.models.Movie
import com.example.mad_exercise1_composable.reposirories.MovieRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movieList = MutableStateFlow(listOf<Movie>())
    val movieList : StateFlow<List<Movie>> = _movieList.asStateFlow()
    var movieID: Int = 1

    init {
        viewModelScope.launch {
            repository.getAllMovies().collect{movieList ->
                _movieList.value = movieList
            }
        }
    }

    fun getMovieByID(movieID: Int) {
        this.movieID = movieID
    }

    suspend fun toggleFavorite(movie: Movie){
        movie.isFavorite = !movie.isFavorite
        repository.update(movie)
    }
}