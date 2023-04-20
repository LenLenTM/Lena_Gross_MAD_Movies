package com.example.mad_exercise1_composable.utils

import android.content.Context
import com.example.mad_exercise1_composable.data.MovieDatabase
import com.example.mad_exercise1_composable.reposirories.MovieRepository
import com.example.mad_exercise1_composable.viewModels.ViewModelFactory

object InjectorUtils {

    private fun getMovieRepository(context: Context): MovieRepository{
        return MovieRepository(MovieDatabase.getDatabase(context).movieDao())
    }

    fun provideMovieViewModelFactory(context: Context): ViewModelFactory{
        val repository = getMovieRepository(context)
        return ViewModelFactory(repository)

    }
}