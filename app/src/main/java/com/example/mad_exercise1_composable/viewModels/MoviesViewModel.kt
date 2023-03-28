package com.example.movieappmad23.viewModels

import androidx.lifecycle.ViewModel
import com.example.mad_exercise1_composable.models.Movie
import com.example.mad_exercise1_composable.models.getMovies

class MoviesViewModel : ViewModel() {
    private val _movieList = getMovies().toMutableList()
    val movieList : List<Movie>
        get() = _movieList

    fun getFavoriteMovieList(): List<Movie>{
        return movieList.filter { it.isFavorite }
    }

    fun toggleFavorite(movieId: String){
        var position: Int = 0;
        _movieList.forEachIndexed{index, it -> if(it.id == movieId){position = index} }

        _movieList[position].isFavorite = !_movieList[position].isFavorite
    }

    fun getFavorite(movieId: String):Boolean{
        var position: Int = 0;
        _movieList.forEachIndexed{index, it -> if(it.id == movieId){position = index}}
        return _movieList[position].isFavorite
    }
}