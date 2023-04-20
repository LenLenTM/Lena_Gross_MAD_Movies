package com.example.mad_exercise1_composable.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mad_exercise1_composable.navigation.Screens
import com.example.mad_exercise1_composable.utils.InjectorUtils
import com.example.mad_exercise1_composable.widgets.MovieRow
import com.example.mad_exercise1_composable.widgets.SimpleAppBar
import com.example.mad_exercise1_composable.viewModels.FavoritesViewModel
import kotlinx.coroutines.launch

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
