package com.example.mad_exercise1_composable.data

import androidx.room.*
import com.example.mad_exercise1_composable.models.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    //CRUD Create Read Update Delete

    @Insert
    suspend fun add(movie: Movie)

    @Update
    suspend fun update(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)

    @Query("SELECT * from movie")
    fun readAll(): Flow<List<Movie>>

    @Query("Select * from movie where isFavorite = 1")
    fun readFavorites(): Flow<List<Movie>>

    @Query("Select * from movie where id=:id")
    fun getMovieByID(id: Int): Flow<List<Movie>>

    @Query("Delete from movie")
    suspend fun deleteAll()
}