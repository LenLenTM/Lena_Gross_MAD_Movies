package com.example.mad_exercise1_composable.screens

import android.annotation.SuppressLint
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
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.mad_exercise1_composable.models.Movie
import com.example.mad_exercise1_composable.models.getMovies
import com.example.mad_exercise1_composable.navigation.Screens
import com.example.mad_exercise1_composable.utils.InjectorUtils
import com.example.mad_exercise1_composable.widgets.MovieRow
import com.example.mad_exercise1_composable.widgets.SimpleAppBar
import com.example.movieappmad23.viewModels.FavoritesViewModel
import com.example.movieappmad23.viewModels.MoviesViewModel
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun FavoriteScreen(navController: NavController){

    val viewModel: FavoritesViewModel = viewModel(factory = InjectorUtils.provideMovieViewModelFactory(
        LocalContext.current))
    val coroutineScope = rememberCoroutineScope()
    val favMovieListState by viewModel.movieListState.collectAsState()

    Column() {
        SimpleAppBar(arrowBackClicked = { navController.popBackStack() }) {
            Text(text = "My Favorite Movies", color = Color.White)
        }
        LazyColumn {
            items(favMovieListState){ movie ->
                MovieRow(
                    movie = movie,
                    onItemClick = { movieId -> navController.navigate(Screens.DetailScreen.route + "/" + movieId) },
                    onFavClick = {
                        coroutineScope.launch { viewModel.toggleFavorite(movie) }})
            }
        }
    }
}