package com.example.mad_exercise1_composable.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.mad_exercise1_composable.models.Movie
import com.example.mad_exercise1_composable.models.getMovies
import com.example.mad_exercise1_composable.navigation.Screens
import com.example.mad_exercise1_composable.widgets.MovieRow
import com.example.mad_exercise1_composable.widgets.SimpleAppBar
import com.example.movieappmad23.viewModels.MoviesViewModel

@Composable
fun FavoriteScreen(navController: NavController, moviesViewModel: MoviesViewModel){

    val favMovies : List<Movie> = moviesViewModel.getFavoriteMovieList();

    Column() {
        SimpleAppBar(arrowBackClicked = { navController.popBackStack() }) {
            Text(text = "My Favorite Movies", color = Color.White)
        }
        LazyColumn {
            items(favMovies){ movie ->
                MovieRow(
                    movie = movie,
                    onItemClick = { movieId -> navController.navigate(Screens.DetailScreen.route + "/" + movieId) },
                    onFavClick = { moviesViewModel.toggleFavorite(it) })
            }
        }
    }
}
