package com.example.mad_exercise1_composable.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_exercise1_composable.models.Movie
import com.example.mad_exercise1_composable.models.getMovies
import com.example.mad_exercise1_composable.reposirories.MovieRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MoviesViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movieList = MutableStateFlow(listOf<Movie>())
    val movieList : StateFlow<List<Movie>> = _movieList.asStateFlow()

    suspend fun addMovies(){
        if (_movieList.value.none { it.title == "Avatar" }){
            getMovies().forEach{ movie -> repository.add(movie)}
        }
    }

    init {
        viewModelScope.launch {
            addMovies()
            repository.getAllMovies()
                .collect{movieList -> _movieList.value = movieList
            }
        }
    }

    suspend fun toggleFavorite(movie: Movie){
        movie.isFavorite = !movie.isFavorite
        repository.update(movie)
    }
}