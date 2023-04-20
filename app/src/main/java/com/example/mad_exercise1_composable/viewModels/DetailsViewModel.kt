package com.example.movieappmad23.viewModels

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_exercise1_composable.models.Movie
import com.example.mad_exercise1_composable.models.getMovies
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
                if(!movieList.isNullOrEmpty()) {
                    _movieList.value = movieList
                }
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