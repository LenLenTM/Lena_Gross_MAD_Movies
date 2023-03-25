package com.example.mad_exercise1_composable.navigation

sealed class Screens(val route: String){
    object HomeScreen: Screens(route = "homeScreen")
    object DetailScreen: Screens(route = "detailScreen/{movieId}")
    object FavoriteScreen: Screens(route = "favoriteScreen")

}
