package com.example.mad_exercise1_composable

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mad_exercise1_composable.screens.DetailScreen
import com.example.mad_exercise1_composable.screens.MovieList

@Composable
fun Navigation(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.route
    ){
        composable(
            route = Screens.HomeScreen.route
        ){
            MovieList(navController = navController)
        }
        composable(
            route = Screens.DetailScreen.route + "/{movieId}",
            arguments = listOf(navArgument("movieId"){
                type = NavType.StringType
            })
        ){
            var movieId = it.arguments?.getString("movieId").toString()
            DetailScreen(movieId = movieId)
        }
    }
}