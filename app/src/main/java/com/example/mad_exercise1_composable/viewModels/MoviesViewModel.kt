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

    fun toggleFavorite(movieId: String){
        var position: Int = 0;
        _movieList.forEachIndexed{index, it -> if(it.id == movieId){position = index} }

        _movieList[position].isFavorite = !_movieList[position].isFavorite
    }

    fun validateTitle(title: String): Boolean{
        return title.isNotEmpty()
    }

    fun validateYear(year: String): Boolean{
        return year.isNotEmpty()
    }

    fun validateGenres(){

    }

    fun validateDirector(director: String): Boolean{
        return director.isNotEmpty()
    }

    fun validateActors(actors: String): Boolean{
        return actors.isNotEmpty()
    }

    fun validatePlot(plot: String): Boolean{
        return plot.isNotEmpty()
    }

    fun validateRating(rating: Float): Boolean{
        return rating.toString().isNotEmpty()
    }
}