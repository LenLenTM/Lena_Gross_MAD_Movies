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

class AddMovieViewModel(private val repository: MovieRepository) : ViewModel() {
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

    suspend fun addMovie(movie : Movie){
        repository.add(movie)
    }

    fun validateMovie(movie: Movie): List<Boolean>{
        var errorMessages = mutableListOf(false, false, false, false, false, false, false)

        when {movie.title.isEmpty() -> errorMessages[0] = true}
        when {movie.year.isEmpty() -> errorMessages[1] = true}
        when {movie.genre.isEmpty() -> errorMessages[2] = true}
        when {movie.director.isEmpty() -> errorMessages[3] = true}
        when {movie.actors.isEmpty() -> errorMessages[4] = true}
        when {movie.plot.isEmpty() -> errorMessages[5] = true}
        when {movie.rating.toString().isEmpty() -> errorMessages[6] = true}
        when {movie.rating == 0.0f -> errorMessages[6] = true}

        return errorMessages
    }
}