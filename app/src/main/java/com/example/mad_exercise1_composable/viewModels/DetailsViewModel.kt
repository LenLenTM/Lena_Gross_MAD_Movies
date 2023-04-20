package com.example.mad_exercise1_composable.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_exercise1_composable.models.Movie
import com.example.mad_exercise1_composable.reposirories.MovieRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movieList = MutableStateFlow(listOf<Movie>())
    val movieList : StateFlow<List<Movie>>
        get() = _movieList

    init {
        viewModelScope.launch {
            repository.getAllMovies().collect{movieList ->
                _movieList.value = movieList
            }
        }
    }

    fun getMovieByID(movieID: Int): Movie {
        return _movieList.value.find { it.id == movieID }!!
    }

    suspend fun toggleFavorite(movie: Movie){
        movie.isFavorite = !movie.isFavorite
        repository.update(movie)
    }
}