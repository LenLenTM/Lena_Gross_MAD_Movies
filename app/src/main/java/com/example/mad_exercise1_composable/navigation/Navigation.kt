package com.example.mad_exercise1_composable.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mad_exercise1_composable.screens.AddMovieScreen
import com.example.mad_exercise1_composable.screens.DetailScreen
import com.example.mad_exercise1_composable.screens.FavoriteScreen
import com.example.mad_exercise1_composable.screens.HomeScreen

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
            val movieId = it.arguments!!.getInt("movieId")
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