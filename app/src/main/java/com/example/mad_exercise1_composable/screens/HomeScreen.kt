package com.example.mad_exercise1_composable.screens

import android.util.Log
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.mad_exercise1_composable.navigation.Screens
import com.example.mad_exercise1_composable.models.Movie
import com.example.mad_exercise1_composable.models.getMovies
import com.example.movieappmad23.viewModels.MoviesViewModel

@Composable
fun MovieList(movies: List<Movie> = getMovies(), navController: NavHostController, moviesViewModel: MoviesViewModel){

    Column() {
        TopAppBar(navController)
        LazyColumn{
            items(movies){movie -> MovieRow(moviesViewModel = moviesViewModel, movie = movie){movieId -> navController.navigate(Screens.DetailScreen.route + "/" + movieId)}
                Modifier.padding(vertical = 10.dp)}
        }
    }
}

@Composable
fun TopAppBar(navController: NavHostController) {

    var expanded by remember{ mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(text = "Movies",
                color = Color.White
            )
        },
        actions = {
            IconButton(onClick = {
                expanded = true
            }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "DropDown",
                    tint = Color.White
                )
            }
            DropdownMenu(expanded = expanded,
                onDismissRequest = { expanded = false }) {
                DropdownMenuItem(onClick = {navController.navigate(Screens.FavoriteScreen.route)}) {
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
                DropdownMenuItem(onClick = {navController.navigate(Screens.AddMovieScreen.route)}) {
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
        },
        backgroundColor = Color.Black
    )
}

@Composable
fun MovieRow(moviesViewModel: MoviesViewModel, movie: Movie, onItemClick : (String) -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { onItemClick(movie.id) },
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        elevation = 5.dp
    ) {
        var details by remember {
            mutableStateOf(true)
        }

        var isFav by remember {
            mutableStateOf(moviesViewModel.getFavorite(movie.id))
        }

        Column {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(Color.Cyan),
                Alignment.TopEnd
            ) {
                Box {
                    AsyncImage(
                        model = movie.images[0],
                        contentDescription = "Picture from the move " + movie.title,
                        contentScale = ContentScale.Crop
                    )
                }
                IconButton(onClick = {
                    moviesViewModel.toggleFavorite(movie.id)
                    isFav = !isFav
                }) {
                    Icon(
                        imageVector = if (isFav) {
                            Icons.Default.Favorite
                        } else {
                            Icons.Default.FavoriteBorder
                        },
                        contentDescription = "",
                        Modifier
                            .align(Alignment.TopEnd)
                            .padding(vertical = 7.dp, horizontal = 7.dp),
                        tint = Color.White
                    )
                }
            }
            Row(
                Modifier
                    .height(40.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = movie.title,
                    Modifier
                        .padding(horizontal = 6.0.dp, vertical = 5.0.dp),
                    fontSize = 20.sp
                )
                IconButton(
                    onClick = {
                        details = !details
                    }
                ) {
                    Icon(
                        imageVector = if (details) {
                            Icons.Default.KeyboardArrowUp
                        } else {
                            Icons.Default.KeyboardArrowDown
                        },
                        ""
                    )
                }
            }
            AnimatedVisibility(visible = !details) {
                Column {
                    Text(
                        text = movie.title + "  " + movie.year + " (" + movie.genre + ")",
                        Modifier
                            .padding(horizontal = 6.0.dp, vertical = 5.0.dp)
                    )

                    Text(
                        text = movie.director + "   Rating: " + movie.rating,
                        Modifier
                            .padding(horizontal = 6.0.dp, vertical = 5.0.dp),
                        fontSize = 12.sp
                    )

                    Text(
                        text = movie.actors,
                        Modifier
                            .padding(horizontal = 6.0.dp, vertical = 5.0.dp),
                        fontSize = 12.sp
                    )

                    Divider(
                        Modifier
                            .padding(horizontal = 6.dp)
                    )

                    Text(
                        text = movie.plot + "\n",
                        Modifier
                            .padding(horizontal = 6.0.dp, vertical = 7.0.dp),
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

