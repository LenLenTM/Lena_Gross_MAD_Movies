package com.example.mad_exercise1_composable.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mad_exercise1_composable.screens.AddMovieScreen
import com.example.mad_exercise1_composable.screens.DetailScreen
import com.example.mad_exercise1_composable.screens.FavoriteScreen
import com.example.mad_exercise1_composable.screens.HomeScreen
import com.example.mad_exercise1_composable.utils.InjectorUtils
import com.example.movieappmad23.viewModels.MoviesViewModel

@Composable
fun Navigation(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.route
    ){
        composable(
            route = Screens.HomeScreen.route
        ){
            HomeScreen(navController = navController)
        }
        composable(
            route = Screens.DetailScreen.route + "/{movieId}",
            arguments = listOf(
                navArgument("movieId"){type = NavType.StringType },
            )
        ){
            var movieId = it.arguments!!.getInt("movieId")
            DetailScreen(navController = navController, movieId = movieId)
        }
        composable(
            route = Screens.FavoriteScreen.route
        ){
            FavoriteScreen(navController = navController)
        }
        composable(
            route = Screens.AddMovieScreen.route
        ){
            AddMovieScreen(navController = navController)
        }
    }
}