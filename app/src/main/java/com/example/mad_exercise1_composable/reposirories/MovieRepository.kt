package com.example.mad_exercise1_composable.reposirories

import com.example.mad_exercise1_composable.data.MovieDao
import com.example.mad_exercise1_composable.models.Movie
import kotlinx.coroutines.flow.first

class MovieRepository(private val movieDao: MovieDao) {

    suspend fun add(movie: Movie) = movieDao.add(movie)

    suspend fun delete(movie: Movie) = movieDao.delete(movie)

    suspend fun update(movie: Movie) = movieDao.update(movie)

    fun getAllMovies() = movieDao.readAll()

    fun getAllFavorites() = movieDao.readFavorites()

    suspend fun getMovieByID(id: Int) = movieDao.getMovieByID(id)
}