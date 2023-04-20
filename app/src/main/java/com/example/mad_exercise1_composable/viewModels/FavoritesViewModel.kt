package com.example.movieappmad23.viewModels

import android.util.Log
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_exercise1_composable.models.Movie
import com.example.mad_exercise1_composable.models.getMovies
import com.example.mad_exercise1_composable.reposirories.MovieRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FavoritesViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movieListState = MutableStateFlow(listOf<Movie>())
    val movieListState : StateFlow<List<Movie>> = _movieListState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllFavorites()
                .collect{listOfMovies -> _movieListState.value = listOfMovies}
        }
    }

    suspend fun toggleFavorite(movie: Movie){
        movie.isFavorite = !movie.isFavorite
        repository.update(movie)
    }
}