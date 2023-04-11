package com.example.mad_exercise1_composable.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.mad_exercise1_composable.navigation.Screens
import com.example.mad_exercise1_composable.models.Movie
import com.example.mad_exercise1_composable.models.getMovies
import com.example.mad_exercise1_composable.widgets.HomeAppBar
import com.example.mad_exercise1_composable.widgets.MovieRow
import com.example.movieappmad23.viewModels.MoviesViewModel

@Composable
fun HomeScreen(navController: NavController, moviesViewModel: MoviesViewModel){
    Column() {
        HomeAppBar(
            title = "Home",
            dropDownContent = {
                DropdownMenuItem(onClick = { navController.navigate(Screens.FavoriteScreen.route) }) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .width(120.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = "",
                            Modifier
                                .padding(vertical = 6.dp),
                            tint = Color.DarkGray
                        )
                        Text(
                            text = "Favorites",
                            Modifier
                                .padding(horizontal = 2.0.dp, vertical = 5.0.dp),
                            fontSize = 18.sp
                        )
                    }
                }
                DropdownMenuItem(onClick =
                { navController.navigate(Screens.AddMovieScreen.route) }) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .width(120.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "",
                            Modifier
                                .padding(vertical = 6.dp),
                            tint = Color.DarkGray
                        )
                        Text(
                            text = "AddMovie",
                            Modifier
                                .padding(horizontal = 2.0.dp, vertical = 5.0.dp),
                            fontSize = 18.sp
                        )
                    }
                }
            }
        )
        LazyColumn {
            items(moviesViewModel.movieList) { movie ->
                MovieRow(
                    movie = movie,
                    onItemClick = { movieId -> navController.navigate(Screens.DetailScreen.route + "/" + movieId) },
                    onFavClick = { moviesViewModel.toggleFavorite(it) })
                Modifier.padding(vertical = 10.dp)
            }
        }
    }
}