package com.example.mad_exercise1_composable.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mad_exercise1_composable.R
import com.example.mad_exercise1_composable.models.Movie

@Composable
fun MovieRow(movie: Movie, onItemClick : (Int) -> Unit, onFavClick: (movie: Movie) -> Unit = {}) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { onItemClick(movie.id) },
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        elevation = 5.dp
    ) {
        Column {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(Color.Cyan),
                contentAlignment = Alignment.TopEnd
            ) {
                if (movie.images[0] == "default")
                    Box {
                        Image(
                            painter = painterResource(id = R.drawable.image),
                            contentDescription = "Picture",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                else MovieImage(imageUrl = movie.images[0])
                Favorite(onFavClick, movie)
            }
            MovieDetails(movie = movie)
        }
    }
}

@Composable
fun MovieImage(imageUrl: String){
    Box {
        AsyncImage(
            model = imageUrl,
            contentDescription = "Picture",
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun Favorite(onFavClick: (Movie) -> Unit, movie: Movie){

    IconButton(onClick = {
        onFavClick(movie)
    }) {
        Icon(
            imageVector = if (movie.isFavorite) {
                Icons.Default.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = "",
            Modifier
                .padding(vertical = 7.dp, horizontal = 7.dp),
            tint = Color.White
        )
    }
}

@Composable
fun MovieDetails(movie: Movie){

    var details by remember {
        mutableStateOf(false)
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
    AnimatedVisibility(visible = details) {
        Column {
            Text(
                text = movie.title + "  " + movie.year + " " + movie.genre + "",
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