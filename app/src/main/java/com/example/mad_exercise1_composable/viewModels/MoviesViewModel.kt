package com.example.movieappmad23.viewModels

import android.util.Log
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.mad_exercise1_composable.models.Movie
import com.example.mad_exercise1_composable.models.getMovies

class MoviesViewModel : ViewModel() {
    private val _movieList = getMovies().toMutableStateList()
    val movieList : List<Movie>
        get() = _movieList

    fun getFavoriteMovieList(): List<Movie>{
        return _movieList.filter { it.isFavorite }
    }

    fun toggleFavorite(movie: Movie){
        _movieList.find{it.id == movie.id}?.let{movie.isFavorite = !movie.isFavorite}
    }

    fun addMovie(movie : Movie){
        _movieList.add(movie)
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

        Log.i("EM", errorMessages.toString())

        return errorMessages
    }
}