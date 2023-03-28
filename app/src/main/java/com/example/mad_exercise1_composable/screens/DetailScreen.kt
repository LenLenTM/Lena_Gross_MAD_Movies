package com.example.mad_exercise1_composable.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.mad_exercise1_composable.models.Movie
import com.example.mad_exercise1_composable.models.getMovies
import com.example.mad_exercise1_composable.widgets.SimpleAppBar
import com.example.movieappmad23.viewModels.MoviesViewModel

@Composable
fun DetailScreen(navController: NavHostController, movieId: String, moviesViewModel: MoviesViewModel){

    val movies: List<Movie>  = getMovies().filter { it.id == movieId }
    val movie: Movie = movies[0]

    Column {
        SimpleAppBar(title = movie.title, navController = navController)
        MovieRow(
            movie = movie,
            onItemClick = {},
            moviesViewModel = moviesViewModel
        )
        Spacer(
            modifier = Modifier.size(20.dp))

        Text(
            text = "Movie Images",
            Modifier
                .fillMaxWidth(),
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        Spacer(
            modifier = Modifier.size(20.dp))

        LazyRow{
            items(movie.images){image -> MovieImages(url = image)}
        }
    }
}

@Composable
fun MovieImages(url: String){
    Card (
        modifier = Modifier
            .fillMaxHeight()
            .padding(5.dp),
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        elevation = 5.dp){

        AsyncImage(
            model = url,
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
    }
}