package com.example.mad_exercise1_composable.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.mad_exercise1_composable.models.Movie
import com.example.mad_exercise1_composable.models.getMovies
import com.example.mad_exercise1_composable.navigation.Screens
import com.example.mad_exercise1_composable.utils.InjectorUtils
import com.example.mad_exercise1_composable.widgets.MovieRow
import com.example.mad_exercise1_composable.widgets.SimpleAppBar
import com.example.movieappmad23.viewModels.AddMovieViewModel
import com.example.movieappmad23.viewModels.DetailsViewModel
import com.example.movieappmad23.viewModels.MoviesViewModel
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DetailScreen(navController: NavController, movieId: Int){

    val viewModel: DetailsViewModel = viewModel(factory = InjectorUtils.provideMovieViewModelFactory(
        LocalContext.current))

    val coroutineScope = rememberCoroutineScope()
    val movie = viewModel.getMovieByID(movieId)

    //coroutineScope.launch { movie = viewModel.getMovieByID(movieId). }

    Column {
        SimpleAppBar(arrowBackClicked = {navController.navigateUp()}){
            Text(text = movie.title, color = Color.White)
        }
        MovieRow(
            movie = movie,
            onItemClick = {movieId -> navController.navigate(Screens.DetailScreen.route + "/" + movieId)},
            onFavClick = {coroutineScope.launch{ viewModel.toggleFavorite(it)}}
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