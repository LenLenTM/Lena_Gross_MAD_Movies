package com.example.mad_exercise1_composable.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.mad_exercise1_composable.models.getMovies
import com.example.mad_exercise1_composable.navigation.Screens
import com.example.mad_exercise1_composable.widgets.SimpleAppBar

@Composable
fun FavoriteScreen(navController: NavHostController){
    Column {
        SimpleAppBar(title = "Favorites", navController = navController)
        LazyColumn {
            items(getMovies()) { movie ->
                MovieRow(movie = movie) { movieId -> navController.navigate(Screens.DetailScreen.route + "/" + movieId)}
            }
        }
    }
}